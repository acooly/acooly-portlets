/*
 * www.acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2019-01-14 21:54 创建
 */
package com.acooly.portlets.feedback.openapi.event;

import com.acooly.core.common.facade.InfoBase;
import com.acooly.portlets.feedback.client.dto.FeedbackInfo;
import lombok.Data;

/**
 * 反馈处理成功事件
 *
 * @author zhangpu 2019-01-14 21:54
 */
@Data
public class FeedbackHandleSuccessEvent extends InfoBase {

    /**
     * 反馈信息
     */
    private FeedbackInfo feedbackInfo;

    public FeedbackHandleSuccessEvent() {
    }

    public FeedbackHandleSuccessEvent(FeedbackInfo feedbackInfo) {
        this.feedbackInfo = feedbackInfo;
    }

}
