/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.feedback.core;

import com.acooly.portlets.feedback.client.dto.FeedbackApplyInfo;
import com.acooly.portlets.feedback.client.dto.FeedbackInfo;
import com.acooly.portlets.feedback.client.enums.FeedbackTypeEnum;
import com.acooly.portlets.feedback.core.service.FeedbackService;
import com.acooly.test.NoWebTestBase;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class FeedbackServiceTest extends NoWebTestBase {


    @Autowired
    private FeedbackService feedbackService;

    String busiType = "hunlizhe-case";


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
        applyInfo.setContactInfo("");
        FeedbackInfo feedbackInfo = feedbackService.apply(applyInfo);
        log.info("Feedback Apply result: {}", JSON.toJSONString(feedbackInfo, true));
    }

}
