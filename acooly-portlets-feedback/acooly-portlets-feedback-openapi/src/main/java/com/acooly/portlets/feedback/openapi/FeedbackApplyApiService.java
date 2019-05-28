/*
 * www.acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2019-01-15 01:16 创建
 */
package com.acooly.portlets.feedback.openapi;

import com.acooly.openapi.framework.common.annotation.ApiDocNote;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.portlets.common.PortletsContants;
import com.acooly.portlets.feedback.client.facade.api.FeedbackFacade;
import com.acooly.portlets.feedback.client.facade.order.FeedbackApplyOrder;
import com.acooly.portlets.feedback.client.openapi.message.FeedbackApplyApiRequest;
import com.acooly.portlets.feedback.client.openapi.message.FeedbackApplyApiResponse;
import com.acooly.portlets.feedback.core.service.FeedbackService;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 反馈提交 api接口
 *
 * @author zhangpu 2019-01-15 01:16
 */
@Slf4j
@ApiDocType(code = PortletsContants.APIDOC_TYPE_CODE, name = PortletsContants.APIDOC_TYPE_NAME)
@ApiDocNote("反馈提交服务，自少必须填写反馈类型和内容")
@OpenApiService(name = "feedbackApply", desc = "反馈提交", owner = "zhangpu")
public class FeedbackApplyApiService extends BaseApiService<FeedbackApplyApiRequest, FeedbackApplyApiResponse> {

    /**
     * 本地服务
     */
    @Autowired(required = false)
    private FeedbackService feedbackService;

    @Reference(version = "1.0")
    private FeedbackFacade feedbackFacade;

    @Override
    protected void doService(FeedbackApplyApiRequest request, FeedbackApplyApiResponse response) {
        if (feedbackService != null) {
            feedbackService.apply(request.getFeedbackApplyInfo());
        } else {
            FeedbackApplyOrder order = new FeedbackApplyOrder(request.getFeedbackApplyInfo());
            feedbackFacade.apply(order);
        }
    }
}
