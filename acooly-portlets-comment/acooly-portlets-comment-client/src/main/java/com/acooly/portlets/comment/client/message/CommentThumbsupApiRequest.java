package com.acooly.portlets.comment.client.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

/**
 * @author zhangpu
 * @date 2019-01-07 00:56
 */
@Data
public class CommentThumbsupApiRequest extends ApiRequest {
    @NotNull
    @OpenApiField(desc = "评论ID", constraint = "评论唯一标志", demo = "11")
    private Long id;
}
