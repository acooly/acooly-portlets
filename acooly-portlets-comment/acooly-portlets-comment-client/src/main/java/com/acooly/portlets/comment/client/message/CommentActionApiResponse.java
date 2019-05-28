package com.acooly.portlets.comment.client.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.portlets.comment.client.dto.CommentInfo;
import com.acooly.portlets.comment.client.dto.CommentLogInfo;
import lombok.Data;

/**
 * @author zhangpu
 * @date 2019-01-07 00:58
 */
@Data
public class CommentActionApiResponse extends ApiResponse {

    @OpenApiField(desc = "评论信息", constraint = "被操作评论信息", ordinal = 1)
    private CommentInfo commentInfo;

    @OpenApiField(desc = "操作日志信息", constraint = "操作日志信息", ordinal = 2)
    private CommentLogInfo commentLogInfo;
}
