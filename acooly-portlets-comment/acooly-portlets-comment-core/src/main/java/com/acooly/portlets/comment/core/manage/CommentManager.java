/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by acooly
 * date:2019-01-06
 *
 */
package com.acooly.portlets.comment.core.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.portlets.comment.core.entity.Comment;

/**
 * p_comment Service接口
 * <p>
 * Date: 2019-01-06 19:37:26
 *
 * @author acooly
 */
public interface CommentManager extends EntityService<Comment> {


    Comment lockComment(Long id);

}
