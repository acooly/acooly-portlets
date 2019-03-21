/*
 * www.acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2019-01-15 00:04 创建
 */
package com.acooly.portlets.test.feedback.core;

import com.acooly.module.event.EventHandler;
import com.acooly.portlets.feedback.core.event.FeedbackApplySuccessEvent;
import com.acooly.portlets.feedback.core.event.FeedbackHandleSuccessEvent;
import com.alibaba.druid.support.json.JSONUtils;
import lombok.extern.slf4j.Slf4j;
import net.engio.mbassy.listener.Handler;
import net.engio.mbassy.listener.Invoke;

/**
 * @author zhangpu 2019-01-15 00:04
 */
@Slf4j
@EventHandler
public class TestFeedbackEventHandler {


    //同步事件处理器
    @Handler
    public void handleFeedbackApplySuccessEvent(FeedbackApplySuccessEvent event) {
        log.info("FeedbackApplySuccessEvent Handler: \n FeedbackApplySuccessEvent:{}", JSONUtils.toJSONString(event));
    }

    //异步事件处理器
    @Handler(delivery = Invoke.Asynchronously)
    public void handleFeedbackHandleSuccessEvent(FeedbackHandleSuccessEvent event) {
        log.info("FeedbackHandleSuccessEvent Handler: \n FeedbackHandleSuccessEvent:{}", JSONUtils.toJSONString(event));
    }
}
