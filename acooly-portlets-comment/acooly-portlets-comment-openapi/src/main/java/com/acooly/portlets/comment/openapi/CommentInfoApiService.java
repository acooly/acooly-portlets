package com.acooly.portlets.comment.openapi;

import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiNote;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.portlets.comment.client.message.CommentInfoApiRequest;
import com.acooly.portlets.comment.client.message.CommentInfoApiResponse;
import com.acooly.portlets.comment.core.service.CommentService;
import com.acooly.portlets.common.PortletsContants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 发表评论
 *
 * @author zhangpu
 * @date 2019-01-07 01:04
 */
@Slf4j
@OpenApiNote("单个评论详情查询接口。")
@ApiDocType(code = PortletsContants.APIDOC_TYPE_CODE, name = PortletsContants.APIDOC_TYPE_NAME)
@OpenApiService(name = "commentInfo", desc = "评论详情", owner = "zhangpu")
public class CommentInfoApiService extends BaseApiService<CommentInfoApiRequest, CommentInfoApiResponse> {

    @Autowired
    private CommentService commentService;

    @Override
    protected void doService(CommentInfoApiRequest request, CommentInfoApiResponse response) {
        response.setCommentInfo(commentService.load(request.getCommentId()));
    }
}
