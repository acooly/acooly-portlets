/*
 * www.acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2019-01-07 18:37 创建
 */
package com.acooly.portlets.comment.client.openapi;

import com.acooly.portlets.comment.client.message.*;

/**
 * 评论组件 openApi客户端服务
 *
 * @author zhangpu 2019-01-07 18:37
 */
public interface CommentApiClientService {

    /**
     * 评论发表
     *
     * @param request
     * @return
     */
    CommentApiResponse comment(CommentApiRequest request);

    /**
     * 评论点赞
     *
     * @param request
     * @return
     */
    CommentThumbsupApiResponse commentThumbsup(CommentThumbsupApiRequest request);


    /**
     * 评论查询
     *
     * @param request
     * @return
     */
    CommentListApiResponse commentList(CommentListApiRequest request);

}
