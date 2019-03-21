/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.feedback.core;

import com.acooly.openapi.framework.core.test.AbstractApiServieTests;
import com.acooly.portlets.feedback.client.dto.FeedbackApplyInfo;
import com.acooly.portlets.feedback.client.enums.FeedbackTypeEnum;
import com.acooly.portlets.feedback.client.openapi.message.FeedbackApplyApiRequest;
import com.acooly.portlets.feedback.client.openapi.message.FeedbackApplyApiResponse;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class FeedbackOpenApiTest extends AbstractApiServieTests {

    {
        this.gatewayUrl = "http://127.0.0.1:8080/gateway.do";
    }


    @Test
    public void testFeedbackApply() {
        FeedbackApplyInfo applyInfo = new FeedbackApplyInfo(FeedbackTypeEnum.suggest,
                "这是建议的内容主体这是建议的内容主体这是建议的内容主体");
        applyInfo.setTitle("可以没有标题的");
        applyInfo.setUserKey("zhangpu");
        applyInfo.setUserName("zhangpu");
        applyInfo.setUserIp("218.201.78.71");
        applyInfo.setPhoneNo("13896177630");
        applyInfo.setMail("zhangpu@acooly.cn");
        applyInfo.setAddress("asdfasdfasdf地址弟弟");
        FeedbackApplyApiRequest request = new FeedbackApplyApiRequest();
        request.setFeedbackApplyInfo(applyInfo);
        FeedbackApplyApiResponse response = request(request, FeedbackApplyApiResponse.class);
        log.info("Feedback Apply result: {}", JSON.toJSONString(response, true));
    }

}
