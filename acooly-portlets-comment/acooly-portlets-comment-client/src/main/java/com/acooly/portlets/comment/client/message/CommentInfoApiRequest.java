package com.acooly.portlets.comment.client.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 单个评论详情查询
 *
 * @author zhangpu
 * @date 2019-01-07 00:56
 */
@Data
public class CommentInfoApiRequest extends ApiRequest {


    /**
     * 评论ID
     * 查询时有效，添加时为空
     */
    @OpenApiField(desc = "评论ID", constraint = "查询评论ID", demo = "11", ordinal = 1)
    @NotNull
    private Long commentId;


}
