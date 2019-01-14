/*
 * www.acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2019-01-15 00:56 创建
 */
package com.acooly.portlets.feedback.client.facade.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.portlets.feedback.client.dto.FeedbackApplyInfo;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author zhangpu 2019-01-15 00:56
 */
@Data
public class FeedbackApplyOrder extends OrderBase {

    @NotNull
    private FeedbackApplyInfo FeedbackApplyInfo;

    public FeedbackApplyOrder() {
    }

    public FeedbackApplyOrder(com.acooly.portlets.feedback.client.dto.FeedbackApplyInfo feedbackApplyInfo) {
        FeedbackApplyInfo = feedbackApplyInfo;
    }
}
