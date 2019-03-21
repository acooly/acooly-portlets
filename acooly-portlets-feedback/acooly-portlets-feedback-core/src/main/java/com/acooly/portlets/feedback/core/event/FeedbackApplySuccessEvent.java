/*
 * www.acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2019-01-14 21:54 创建
 */
package com.acooly.portlets.feedback.core.event;

import com.acooly.core.common.facade.InfoBase;
import com.acooly.portlets.feedback.client.dto.FeedbackInfo;
import lombok.Data;

/**
 * 反馈提交成功事件
 *
 * @author zhangpu 2019-01-14 21:54
 */
@Data
public class FeedbackApplySuccessEvent extends InfoBase {

    /**
     * 反馈信息
     */
    private FeedbackInfo feedbackInfo;

    public FeedbackApplySuccessEvent() {
    }

    public FeedbackApplySuccessEvent(FeedbackInfo feedbackInfo) {
        this.feedbackInfo = feedbackInfo;
    }
}
