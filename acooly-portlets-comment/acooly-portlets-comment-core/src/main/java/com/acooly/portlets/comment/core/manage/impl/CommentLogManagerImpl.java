/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by acooly
 * date:2019-01-09
 */
package com.acooly.portlets.comment.core.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.portlets.comment.client.enums.CommentLogActionTypeEnum;
import com.acooly.portlets.comment.core.dao.CommentLogDao;
import com.acooly.portlets.comment.core.entity.CommentLog;
import com.acooly.portlets.comment.core.manage.CommentLogManager;
import org.springframework.stereotype.Service;

/**
 * 评论日志 Service实现
 * <p>
 * Date: 2019-01-09 00:46:41
 *
 * @author acooly
 */
@Service
public class CommentLogManagerImpl extends EntityServiceImpl<CommentLog, CommentLogDao> implements CommentLogManager {


    @Override
    public CommentLog getTopByUserAction(Long commnetId, String userNo, CommentLogActionTypeEnum actionType) {
        return getEntityDao().findByCommentIdAndUserNoAndActionTypeForTop(commnetId, userNo, actionType);
    }
}
