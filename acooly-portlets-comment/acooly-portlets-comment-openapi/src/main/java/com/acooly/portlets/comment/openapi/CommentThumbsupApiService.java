package com.acooly.portlets.comment.openapi;

import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiNote;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.portlets.comment.client.message.CommentThumbsupApiRequest;
import com.acooly.portlets.comment.client.message.CommentThumbsupApiResponse;
import com.acooly.portlets.comment.core.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 发表评论
 *
 * @author zhangpu
 * @date 2019-01-07 01:04
 */
@Slf4j
@OpenApiNote("评论组件的点赞服务接口。")
@ApiDocType(code = "Portlets", name = "前端组件")
@OpenApiService(name = "commentThumbsup", desc = "评论点赞", owner = "zhangpu")
public class CommentThumbsupApiService extends BaseApiService<CommentThumbsupApiRequest, CommentThumbsupApiResponse> {

    @Autowired
    private CommentService commentService;

    @Override
    protected void doService(CommentThumbsupApiRequest request, CommentThumbsupApiResponse response) {
        int thumbsup = commentService.thumbsup(request.getId());
        response.setThumbsup(thumbsup);
    }
}
