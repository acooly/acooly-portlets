/*
 * www.acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2019-01-15 01:06 创建
 */
package com.acooly.portlets.feedback.core.remote;

import com.acooly.portlets.feedback.client.facade.api.FeedbackFacade;
import com.acooly.portlets.feedback.client.facade.order.FeedbackApplyOrder;
import com.acooly.portlets.feedback.client.facade.order.FeedbackHandleOrder;
import com.acooly.portlets.feedback.client.facade.result.FeedbackApplyResult;
import com.acooly.portlets.feedback.client.facade.result.FeedbackHandleResult;
import com.acooly.portlets.feedback.core.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhangpu 2019-01-15 01:06
 */
@Service("feedbackFacade")
public class FeedbackFacadeImpl implements FeedbackFacade {

    @Autowired
    private FeedbackService feedbackService;

    @Override
    public FeedbackApplyResult apply(FeedbackApplyOrder order) {
        return new FeedbackApplyResult(feedbackService.apply(order.getFeedbackApplyInfo()));
    }

    @Override
    public FeedbackHandleResult handle(FeedbackHandleOrder order) {
        return new FeedbackHandleResult(feedbackService.handle(order.getFeedbackHandleInfo()));
    }
}
