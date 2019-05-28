/*
 * www.acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2019-01-15 01:03 创建
 */
package com.acooly.portlets.feedback.client.openapi.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.portlets.feedback.client.dto.FeedbackInfo;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhangpu 2019-01-15 01:03
 */
@Data
public class FeedbackApplyApiResponse extends ApiResponse {

    @NotNull
    @OpenApiField(desc = "反馈信息", constraint = "反馈信息", demo = "JSON结构体",ordinal = 1)
    private FeedbackInfo feedbackInfo;

}
