/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-01-14
 *
 */
package com.acooly.portlets.feedback.core.service;

import com.acooly.portlets.feedback.client.dto.FeedbackApplyInfo;
import com.acooly.portlets.feedback.client.dto.FeedbackHandleInfo;
import com.acooly.portlets.feedback.client.dto.FeedbackInfo;

/**
 * 客户反馈 Service接口
 * <p>
 * Date: 2019-01-14 21:23:31
 *
 * @author zhangpu@acooly.cn
 */
public interface FeedbackService {

    /**
     * 反馈提交
     *
     * @param applyInfo
     * @return
     */
    FeedbackInfo apply(FeedbackApplyInfo applyInfo);


    /**
     * 反馈处理
     *
     * @param handleInfo
     * @return
     */
    FeedbackInfo handle(FeedbackHandleInfo handleInfo);
}
