package com.acooly.portlets.comment.core.service.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.utils.Assert;
import com.acooly.core.utils.Collections3;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.AbleStatus;
import com.acooly.core.utils.enums.WhetherStatus;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.core.utils.validate.Validators;
import com.acooly.module.event.EventBus;
import com.acooly.portlets.comment.client.dto.*;
import com.acooly.portlets.comment.client.enums.CommentErrorCodes;
import com.acooly.portlets.comment.client.enums.CommentLogActionTypeEnum;
import com.acooly.portlets.comment.client.enums.CommentStatusEnum;
import com.acooly.portlets.comment.core.PortletCommentProperties;
import com.acooly.portlets.comment.core.entity.Comment;
import com.acooly.portlets.comment.core.entity.CommentLog;
import com.acooly.portlets.comment.core.manage.CommentLogManager;
import com.acooly.portlets.comment.core.manage.CommentManager;
import com.acooly.portlets.comment.core.service.CommentService;
import com.acooly.portlets.comment.core.service.CommentThumbsupCacheManager;
import com.acooly.portlets.comment.core.service.event.CommentActionSuccessEvent;
import com.acooly.portlets.comment.core.service.event.CommentSuccessEvent;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2019-01-06 22:29
 */
@Slf4j
@Component
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentManager commentManager;

    @Autowired
    private CommentLogManager commentLogManager;

    @Autowired
    PortletCommentProperties portletCommentProperties;

    @Autowired
    private EventBus eventBus;

    @Autowired
    private CommentThumbsupCacheManager commentThumbsupCacheManager;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommentInfo comment(CommentBaseInfo commentBaseInfo) {
        try {
            CommentSuccessEvent event = new CommentSuccessEvent();
            event.setCommentBaseInfo(commentBaseInfo);
            if (commentBaseInfo.getParentId() != null) {
                Comment parent = commentManager.lockComment(commentBaseInfo.getParentId());
                if (parent == null) {
                    log.warn("评论 [失败] 复评的parent不存在");
                    throw new BusinessException(CommonErrorCodes.OBJECT_NOT_EXIST, "复评的parent不存在");
                }
                parent.setRepeats(parent.getRepeats() + 1);
                commentManager.update(parent);
                event.setParent(convert(parent));
            }

            Comment comment = BeanCopier.copy(commentBaseInfo, Comment.class);
            if (Strings.isBlank(comment.getCommentNo())) {
                comment.setCommentNo(Ids.getDid());
            }
            commentManager.save(comment);

            CommentInfo commentInfo = convert(comment);
            event.setCommentInfo(commentInfo);
            eventBus.publish(event);
            return commentInfo;
        } catch (BusinessException be) {
            throw be;
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCodes.INTERNAL_ERROR);
        }
    }


    @Override
    public CommentInfo load(Long commentId) {
        return convert(commentManager.get(commentId));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public CommentActionResult action(CommentActionInfo commentActionInfo) {

        try {
            Validators.assertJSR303(commentActionInfo);
            Comment comment = commentManager.lockComment(commentActionInfo.getCommentId());
            CommentLogInfo commentLogInfo = null;
            if (commentActionInfo.getActionType() == CommentLogActionTypeEnum.report) {
                commentLogInfo = doReportAction(comment, commentActionInfo);
            } else if (commentActionInfo.getActionType() == CommentLogActionTypeEnum.thumbsup) {
                commentLogInfo = doThumbsupAction(comment, commentActionInfo);
            } else if (commentActionInfo.getActionType() == CommentLogActionTypeEnum.thumbsup_cancel) {
                commentLogInfo = doThumbsupCancelAction(comment, commentActionInfo);
            }
            CommentActionResult result = new CommentActionResult(convert(comment), commentLogInfo);

            // 发布事件
            CommentActionSuccessEvent event = new CommentActionSuccessEvent();
            event.setCommentInfo(result.getCommentInfo());
            event.setCommentLogInfo(result.getCommentLogInfo());
            eventBus.publish(event);

            return result;
        } catch (BusinessException be) {
            throw be;
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCodes.INTERNAL_ERROR);
        }
    }

    /**
     * 举报
     *
     * @param commentActionInfo
     * @return
     */
    protected CommentLogInfo doReportAction(Comment comment, CommentActionInfo commentActionInfo) {

        if (comment.getStatus() == CommentStatusEnum.disabled || comment.getStatus() == CommentStatusEnum.enable_report) {
            log.warn("评论举报 [失败] 状态冲突，{}不能进行举报操作", comment.getStatus());
            throw new BusinessException(CommentErrorCodes.COMMENT_STATUS_CONFLICT);
        }
        comment.setStatus(CommentStatusEnum.enable_report);
        commentManager.update(comment);
        CommentLog commentLog = BeanCopier.copy(commentActionInfo, CommentLog.class);
        commentLogManager.save(commentLog);
        return convert(commentLog);
    }

    /**
     * 点赞
     *
     * @param comment
     * @param commentActionInfo
     * @return
     */
    protected CommentLogInfo doThumbsupAction(Comment comment, CommentActionInfo commentActionInfo) {
        // 如果不允许重复点赞
        if (!portletCommentProperties.isThumbsupAllowRepeat()) {
            CommentLog exist = commentLogManager.getTopByUserAction(commentActionInfo.getCommentId(),
                    commentActionInfo.getUserNo(), commentActionInfo.getActionType());
            if (exist != null) {
                log.warn("评论点赞 [失败] 不能重复点赞, commentActionInfo:{}", commentActionInfo);
                throw new BusinessException(CommentErrorCodes.NOT_ALLOWED_THUMBSUP_REPEATED);
            }
        }
        if (comment.getStatus() == CommentStatusEnum.disabled || comment.getStatus() == CommentStatusEnum.enable_report) {
            log.warn("评论点赞 [失败] 状态冲突，{}不能进行评论点赞", comment.getStatus());
            throw new BusinessException(CommentErrorCodes.COMMENT_STATUS_CONFLICT);
        }

        if (Strings.equals(comment.getUserNo(), commentActionInfo.getUserNo())) {
            log.warn("评论点赞 [失败] 不允许为自己的评论点赞，commentId:{}", comment.getId());
            throw new BusinessException(CommentErrorCodes.NOT_ALLOWED_THUMBSUP_YOURSELF);
        }

        comment.setThumbsup(comment.getThumbsup() + 1);
        commentManager.update(comment);
        CommentLog commentLog = BeanCopier.copy(commentActionInfo, CommentLog.class);
        commentLogManager.save(commentLog);
        commentThumbsupCacheManager.add(commentActionInfo.getUserNo(), comment.getId());
        return convert(commentLog);
    }

    /**
     * 取消点赞
     *
     * @param comment
     * @param commentActionInfo
     * @return
     */
    protected CommentLogInfo doThumbsupCancelAction(Comment comment, CommentActionInfo commentActionInfo) {
        // 如果不允许重复点赞
        CommentLog originCommentLog = commentLogManager.getTopByUserAction(commentActionInfo.getCommentId(),
                commentActionInfo.getUserNo(), CommentLogActionTypeEnum.thumbsup);
        if (originCommentLog == null) {
            log.warn("评论取消点赞 [失败] 找不到你的点赞记录, commentActionInfo:{}", commentActionInfo);
            throw new BusinessException(CommonErrorCodes.OBJECT_NOT_EXIST, "找不到你的点赞记录");
        }

        comment.setThumbsup(comment.getThumbsup() - 1);
        if (comment.getThumbsup() < 0) {
            comment.setThumbsup(0);
        }
        commentManager.update(comment);
        originCommentLog.setStatus(AbleStatus.disable);
        commentLogManager.update(originCommentLog);
        commentThumbsupCacheManager.sub(commentActionInfo.getUserNo(), comment.getId());
        return convert(originCommentLog);
    }


    @Override
    public PageInfo<CommentInfo> query(PageInfo<CommentInfo> pageInfo, String userNo, String busiKey, String busiType,
                                       Map<String, Object> map, Map<String, Boolean> sortMap, Boolean queryChild) {
        Assert.notNull(busiKey);
        // 分页查询顶层有效数据
        Map<String, Object> params = Maps.newHashMap();
        params.put("EQ_busiKey", busiKey);
        params.put("EQ_busiType", busiType);
        params.put("RLIKE_status", "enable");
        params.put("NULL_parentId", "");

        if (map != null && map.size() > 0) {
            params.putAll(map);
        }

        Map<String, Boolean> orderMap = Maps.newLinkedHashMap();
        orderMap.put("sticky", false);
        orderMap.put("id", false);

        if (sortMap != null && sortMap.size() > 0) {
            orderMap.putAll(sortMap);
        }
        PageInfo<Comment> pageInfoEntity = new PageInfo<>(pageInfo.getCountOfCurrentPage(), pageInfo.getCurrentPage());
        commentManager.query(pageInfoEntity, params, orderMap);
        pageInfo.setTotalPage(pageInfoEntity.getTotalPage());
        pageInfo.setTotalCount(pageInfoEntity.getTotalCount());

        if (queryChild == null) {
            queryChild = true;
        }

        List<CommentInfo> commentInfos = Lists.newArrayList();
        if (Collections3.isNotEmpty(pageInfoEntity.getPageResults())) {
            CommentInfo commentInfo = null;
            for (Comment comment : pageInfoEntity.getPageResults()) {
                commentInfo = convert(comment);
                if (queryChild && comment.getRepeats() > 0) {
                    commentInfo.setChildren(queryWithParentId(comment.getId(), sortMap));
                }
                commentInfos.add(commentInfo);
            }
        }
        doMarkThumbsup(userNo, commentInfos);
        pageInfo.setPageResults(commentInfos);
        return pageInfo;
    }

    protected void doMarkThumbsup(String actionUserNo, List<CommentInfo> commentInfos) {
        if (Strings.isBlank(actionUserNo)) {
            return;
        }
        List<Long> actionUserThumbsupCommentIds = commentThumbsupCacheManager.get(actionUserNo);
        if (Collections3.isEmpty(actionUserThumbsupCommentIds)) {
            return;
        }

        for (CommentInfo commentInfo : commentInfos) {
            if (actionUserThumbsupCommentIds.contains(commentInfo.getId())) {
                commentInfo.setActionUserNo(actionUserNo);
                commentInfo.setActionThumbsup(WhetherStatus.yes);
            }
            if (Collections3.isNotEmpty(commentInfo.getChildren())) {
                for (CommentInfo subCommentInfo : commentInfo.getChildren()) {
                    if (actionUserThumbsupCommentIds.contains(subCommentInfo.getId())) {
                        subCommentInfo.setActionUserNo(actionUserNo);
                        subCommentInfo.setActionThumbsup(WhetherStatus.yes);
                    }
                }
            }
        }
    }


    protected List<CommentInfo> queryWithParentId(Long parentId, Map<String, Boolean> sortMap) {
        Map<String, Object> params = Maps.newHashMap();
        params.put("EQ_parentId", parentId);
        params.put("RLIKE_status", "enable");
        List<Comment> comments = commentManager.query(params, sortMap);
        List<CommentInfo> commentInfos = null;
        if (Collections3.isNotEmpty(comments)) {
            commentInfos = new ArrayList<>(comments.size());
            for (Comment comment : comments) {
                commentInfos.add(convert(comment));
            }
        }
        return commentInfos;
    }


    private CommentInfo convert(Comment comment) {
        CommentInfo commentInfo = BeanCopier.copy(comment, CommentInfo.class);
        commentInfo.setPubDate(comment.getCreateTime());
        commentInfo.setStatusText(comment.getStatus().getMessage());
        return commentInfo;
    }

    private CommentLogInfo convert(CommentLog commentLog) {
        return BeanCopier.copy(commentLog, CommentLogInfo.class);
    }

}
