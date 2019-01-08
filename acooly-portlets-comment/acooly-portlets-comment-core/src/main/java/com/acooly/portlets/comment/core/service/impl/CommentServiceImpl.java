package com.acooly.portlets.comment.core.service.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.utils.Assert;
import com.acooly.core.utils.Collections3;
import com.acooly.core.utils.Ids;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.module.event.EventBus;
import com.acooly.portlets.comment.client.dto.CommentBaseInfo;
import com.acooly.portlets.comment.client.dto.CommentInfo;
import com.acooly.portlets.comment.core.entity.Comment;
import com.acooly.portlets.comment.core.manage.CommentManager;
import com.acooly.portlets.comment.core.service.CommentService;
import com.acooly.portlets.comment.core.service.CommentSuccessEvent;
import com.acooly.portlets.comment.core.service.CommentThumbsupEvent;
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
    private EventBus eventBus;

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

    /**
     * 点赞
     *
     * @param commentId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Integer thumbsup(Long commentId) {
        try {
            Comment comment = commentManager.lockComment(commentId);
            comment.setThumbsup(comment.getThumbsup() + 1);
            commentManager.update(comment);
            CommentThumbsupEvent event = new CommentThumbsupEvent();
            event.setCommentId(commentId);
            event.setCommentInfo(convert(comment));
            eventBus.publish(event);
            return comment.getThumbsup();
        } catch (BusinessException be) {
            throw be;
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCodes.INTERNAL_ERROR);
        }
    }

    @Override
    public PageInfo<CommentInfo> query(PageInfo<CommentInfo> pageInfo, String busiKey, String busiType,
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
        pageInfo.setTotalCount(pageInfo.getTotalCount());

        if (queryChild == null) {
            queryChild = true;
        }

        if (Collections3.isNotEmpty(pageInfoEntity.getPageResults())) {
            List<CommentInfo> commentInfos = Lists.newArrayList();
            CommentInfo commentInfo = null;
            for (Comment comment : pageInfoEntity.getPageResults()) {
                commentInfo = convert(comment);
                if (queryChild && comment.getRepeats() > 0) {
                    commentInfo.setChildren(queryWithParentId(comment.getId(), sortMap));
                }
                commentInfos.add(commentInfo);
            }
//            commentInfos = QuickTree.quickTree(commentInfos, null);
            pageInfo.setPageResults(commentInfos);
        }
        return pageInfo;
    }


    public List<CommentInfo> queryWithParentId(Long parentId, Map<String, Boolean> sortMap) {
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
}
