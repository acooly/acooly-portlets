package com.acooly.portlets.comment.client.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.portlets.comment.client.dto.CommentInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * 评论详情 响应报文
 * @author zhangpu
 * @date 2019-01-07 00:56
 */
@Getter
@Setter
public class CommentInfoApiResponse extends ApiResponse {

    @OpenApiField(desc = "评论详情", constraint = "评论详情，包含子评论和附件", ordinal = 1)
    private CommentInfo commentInfo;

}
