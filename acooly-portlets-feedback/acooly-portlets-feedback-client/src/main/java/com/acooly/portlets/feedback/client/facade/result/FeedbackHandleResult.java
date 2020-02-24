/*
 * www.acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2019-01-15 00:56 创建
 */
package com.acooly.portlets.feedback.client.facade.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.portlets.feedback.client.dto.FeedbackInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author zhangpu 2019-01-15 00:56
 */
@Getter
@Setter
public class FeedbackHandleResult extends ResultBase {

    @NotNull
    private FeedbackInfo feedbackInfo;

    public FeedbackHandleResult() {
    }

    public FeedbackHandleResult(FeedbackInfo feedbackInfo) {
        this.feedbackInfo = feedbackInfo;
    }
}
