package com.acooly.portlets.comment.openapi;

import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiNote;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.portlets.comment.client.dto.CommentBaseInfo;
import com.acooly.portlets.comment.client.dto.CommentInfo;
import com.acooly.portlets.comment.client.message.CommentApiRequest;
import com.acooly.portlets.comment.client.message.CommentApiResponse;
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
@OpenApiNote("评论组件的发布评论服务。" +
        "<li>1、支持发布子评论（复评，parentId为被复评的评论）</li>" +
        "<li>2、通过自定义busiType和busiKey绑定被评论的对象</li>")
@ApiDocType(code = "Portlets", name = "前端组件")
@OpenApiService(name = "comment", desc = "评论", owner = "zhangpu")
public class CommentApiService extends BaseApiService<CommentApiRequest, CommentApiResponse> {

    @Autowired
    private CommentService commentService;

    @Override
    protected void doService(CommentApiRequest request, CommentApiResponse response) {
        CommentBaseInfo commentBaseInfo = BeanCopier.copy(request, CommentBaseInfo.class);
        CommentInfo commentInfo = commentService.comment(commentBaseInfo);
        BeanCopier.copy(commentInfo, response);
    }
}
