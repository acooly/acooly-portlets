package com.acooly.portlets.comment.openapi;

import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiNote;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.portlets.comment.client.dto.CommentActionInfo;
import com.acooly.portlets.comment.client.dto.CommentActionResult;
import com.acooly.portlets.comment.client.message.CommentActionApiRequest;
import com.acooly.portlets.comment.client.message.CommentActionApiResponse;
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
@OpenApiNote("评论组件的操作服务接口。包括：" +
        "<li>1、CommentLogActionTypeEnum.report: 举报</li>" +
        "<li>1、CommentLogActionTypeEnum.thumbsup: 点赞</li>")
@ApiDocType(code = PortletsContants.APIDOC_TYPE_CODE, name = PortletsContants.APIDOC_TYPE_NAME)
@OpenApiService(name = "commentAction", desc = "评论操作", owner = "zhangpu")
public class CommentActionApiService extends BaseApiService<CommentActionApiRequest, CommentActionApiResponse> {

    @Autowired
    private CommentService commentService;

    @Override
    protected void doService(CommentActionApiRequest request, CommentActionApiResponse response) {
        CommentActionInfo actionInfo = BeanCopier.copy(request, CommentActionInfo.class);
        CommentActionResult result = commentService.action(actionInfo);
        response.setCommentInfo(result.getCommentInfo());
        response.setCommentLogInfo(result.getCommentLogInfo());
    }
}
