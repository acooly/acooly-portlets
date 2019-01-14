/*
 * www.acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2019-01-15 01:00 创建
 */
package com.acooly.portlets.feedback.client.facade.api;

import com.acooly.portlets.feedback.client.facade.order.FeedbackApplyOrder;
import com.acooly.portlets.feedback.client.facade.order.FeedbackHandleOrder;
import com.acooly.portlets.feedback.client.facade.result.FeedbackApplyResult;
import com.acooly.portlets.feedback.client.facade.result.FeedbackHandleResult;

/**
 * 反馈的远程服务facade接口
 *
 * @author zhangpu 2019-01-15 01:00
 */
public interface FeedbackFacade {

    /**
     * 反馈提交
     *
     * @param order
     * @return
     */
    FeedbackApplyResult apply(FeedbackApplyOrder order);


    /**
     * 反馈处理
     *
     * @param order
     * @return
     */
    FeedbackHandleResult handle(FeedbackHandleOrder order);
}
