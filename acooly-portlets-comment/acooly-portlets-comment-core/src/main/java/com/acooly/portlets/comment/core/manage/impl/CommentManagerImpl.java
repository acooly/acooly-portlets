/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by acooly
 * date:2019-01-06
 */
package com.acooly.portlets.comment.core.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.portlets.comment.core.dao.CommentDao;
import com.acooly.portlets.comment.core.entity.Comment;
import com.acooly.portlets.comment.core.manage.CommentManager;
import org.springframework.stereotype.Service;

/**
 * p_comment Service实现
 * <p>
 * Date: 2019-01-06 19:37:26
 *
 * @author acooly
 */
@Service("commentService")
public class CommentManagerImpl extends EntityServiceImpl<Comment, CommentDao> implements CommentManager {


    @Override
    public Comment lockComment(Long id) {
        return getEntityDao().lockComment(id);
    }
}
