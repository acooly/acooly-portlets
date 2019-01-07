package com.acooly.portlets.comment.client.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiResponse;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zhangpu
 * @date 2019-01-07 00:58
 */
@Data
public class CommentThumbsupApiResponse extends ApiResponse {

    @OpenApiField(desc = "点赞数", constraint = "点赞数量", demo = "11")
    private int thumbsup = 0;
}
