/*
 * www.acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2019-01-15 01:06 创建
 */
package com.acooly.portlets.feedback.openapi.remote;

import com.acooly.module.appservice.AppService;
import com.acooly.portlets.feedback.client.facade.api.FeedbackFacade;
import com.acooly.portlets.feedback.client.facade.order.FeedbackApplyOrder;
import com.acooly.portlets.feedback.client.facade.order.FeedbackHandleOrder;
import com.acooly.portlets.feedback.client.facade.result.FeedbackApplyResult;
import com.acooly.portlets.feedback.client.facade.result.FeedbackHandleResult;
import com.acooly.portlets.feedback.openapi.service.FeedbackService;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangpu 2019-01-15 01:06
 */
@Service(version = "1.0")
public class FeedbackFacadeImpl implements FeedbackFacade {

    @Autowired
    private FeedbackService feedbackService;

    @Override
    @AppService
    public FeedbackApplyResult apply(FeedbackApplyOrder order) {
        return new FeedbackApplyResult(feedbackService.apply(order.getFeedbackApplyInfo()));
    }

    @Override
    @AppService
    public FeedbackHandleResult handle(FeedbackHandleOrder order) {
        return new FeedbackHandleResult(feedbackService.handle(order.getFeedbackHandleInfo()));
    }
}
