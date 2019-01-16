package com.acooly.portlets.comment.core.service;

import com.acooly.core.utils.Collections3;
import com.acooly.portlets.comment.client.enums.CommentLogActionTypeEnum;
import com.acooly.portlets.comment.core.entity.CommentLog;
import com.acooly.portlets.comment.core.manage.CommentLogManager;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zhangpu
 * @date 2019-01-15 17:23
 */
@Slf4j
@Component
public class CommentThumbsupCacheManager {

    @Autowired
    private CommentLogManager commentLogManager;

    @Autowired
    private RedisTemplate redisTemplate;


    public List<Long> get(String actionUserNo) {
        String cacheKey = getCacheKey(actionUserNo);
        ValueOperations va = redisTemplate.opsForValue();
        List<Long> commentIds = (List<Long>) va.get(cacheKey);
        if (commentIds == null) {
            commentIds = loadData(actionUserNo);
            va.set(cacheKey, commentIds);
        }
        return commentIds;
    }

    public void set(String actionUserNo, List<Long> commentIds) {
        String cacheKey = getCacheKey(actionUserNo);
        ValueOperations va = redisTemplate.opsForValue();
        va.set(cacheKey, commentIds);
    }

    /**
     * 添加点赞
     *
     * @param actionUserNo
     * @param commentId
     */
    public void add(String actionUserNo, Long commentId) {
        List<Long> ids = get(actionUserNo);
        if (Collections3.isNotEmpty(ids) && !ids.contains(commentId)) {
            ids.add(commentId);
        }
        set(actionUserNo, ids);
    }

    /**
     * 减少点赞
     *
     * @param actionUserNo
     * @param commentId
     */
    public void sub(String actionUserNo, Long commentId) {
        List<Long> ids = get(actionUserNo);
        if (Collections3.isNotEmpty(ids) && ids.contains(commentId)) {
            ids.remove(commentId);
        }
        set(actionUserNo, ids);
    }

    /**
     * 从数据库加载数据
     *
     * @param actionUserNo
     * @return
     */
    protected List<Long> loadData(String actionUserNo) {
        List<CommentLog> commentLogs = commentLogManager.getUserActions(actionUserNo, CommentLogActionTypeEnum.thumbsup);
        List<Long> commentIds = Lists.newArrayList();
        if (Collections3.isNotEmpty(commentLogs)) {
            for (CommentLog commentLog : commentLogs) {
                commentIds.add(commentLog.getCommentId());
            }
        }
        return commentIds;
    }

    /**
     * 缓存key
     *
     * @param actionUserNo
     * @return
     */
    protected String getCacheKey(String actionUserNo) {
        return "comment.thumbsup." + actionUserNo + ".ids";
    }


}
