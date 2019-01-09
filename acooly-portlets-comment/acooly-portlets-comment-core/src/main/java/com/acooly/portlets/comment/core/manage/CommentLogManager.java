/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by acooly
 * date:2019-01-09
 *
 */
package com.acooly.portlets.comment.core.manage;

import com.acooly.core.common.service.EntityService;
import com.acooly.portlets.comment.client.enums.CommentLogActionTypeEnum;
import com.acooly.portlets.comment.core.entity.CommentLog;

/**
 * 评论日志 Service接口
 * <p>
 * Date: 2019-01-09 00:46:41
 *
 * @author acooly
 */
public interface CommentLogManager extends EntityService<CommentLog> {

    CommentLog getTopByUserAction(Long commnetId, String userNo, CommentLogActionTypeEnum actionType);

}
