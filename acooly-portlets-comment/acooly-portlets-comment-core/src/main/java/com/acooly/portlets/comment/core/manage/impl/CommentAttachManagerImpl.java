/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-05-26
 */
package com.acooly.portlets.comment.core.manage.impl;

import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.portlets.comment.core.manage.CommentAttachManager;
import com.acooly.portlets.comment.core.dao.CommentAttachDao;
import com.acooly.portlets.comment.core.entity.CommentAttach;

/**
 * 评论附件 Service实现
 *
 * Date: 2019-05-26 23:54:02
 *
 * @author zhangpu@acooly.cn
 *
 */
@Service("commentAttachService")
public class CommentAttachManagerImpl extends EntityServiceImpl<CommentAttach, CommentAttachDao> implements CommentAttachManager {

}
