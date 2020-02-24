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
import com.acooly.portlets.feedback.client.dto.FeedbackHandleInfo;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author zhangpu 2019-01-15 00:56
 */
@Getter
@Setter
public class FeedbackHandleOrder extends OrderBase {

    @NotNull
    private FeedbackHandleInfo feedbackHandleInfo;


    public FeedbackHandleOrder() {
    }

    public FeedbackHandleOrder(FeedbackHandleInfo feedbackHandleInfo) {
        this.feedbackHandleInfo = feedbackHandleInfo;
    }
}
