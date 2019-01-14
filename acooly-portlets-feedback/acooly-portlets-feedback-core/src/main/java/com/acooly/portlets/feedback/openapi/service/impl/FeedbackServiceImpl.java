/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-01-14
 */
package com.acooly.portlets.feedback.openapi.service.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.common.exception.OrderCheckException;
import com.acooly.core.utils.enums.Messageable;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.core.utils.validate.Validators;
import com.acooly.module.event.EventBus;
import com.acooly.portlets.feedback.client.dto.FeedbackApplyInfo;
import com.acooly.portlets.feedback.client.dto.FeedbackHandleInfo;
import com.acooly.portlets.feedback.client.dto.FeedbackInfo;
import com.acooly.portlets.feedback.openapi.PortletFeedbackProperties;
import com.acooly.portlets.feedback.openapi.entity.Feedback;
import com.acooly.portlets.feedback.openapi.event.FeedbackApplySuccessEvent;
import com.acooly.portlets.feedback.openapi.event.FeedbackHandleSuccessEvent;
import com.acooly.portlets.feedback.openapi.manage.FeedbackManager;
import com.acooly.portlets.feedback.openapi.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 客户反馈 Service实现
 * <p>
 * Date: 2019-01-14 21:23:31
 *
 * @author zhangpu@acooly.cn
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackManager feedbackManager;
    @Autowired
    private EventBus eventBus;
    @Autowired
    private PortletFeedbackProperties portletFeedbackProperties;

    @Override
    public FeedbackInfo apply(FeedbackApplyInfo applyInfo) {
        try {
            Validators.assertJSR303(applyInfo);
            Feedback feedback = BeanCopier.copy(applyInfo, Feedback.class);
            feedbackManager.save(feedback);
            FeedbackInfo feedbackInfo = convert(feedback);

            //发布事件：成功提交后事件
            if (portletFeedbackProperties.isEventPublishEnable()) {
                eventBus.publish(new FeedbackApplySuccessEvent(feedbackInfo));
            }
            return feedbackInfo;
        } catch (OrderCheckException ie) {
            throw new BusinessException((Messageable) ie);
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCodes.INTERNAL_ERROR);
        }
    }

    @Override
    public FeedbackInfo handle(FeedbackHandleInfo handleInfo) {
        try {
            Validators.assertJSR303(handleInfo);
            Feedback feedback = feedbackManager.get(handleInfo.getId());
            feedback.setReplyContent(handleInfo.getReplyContent());
            feedback.setReplyUser(handleInfo.getReplyUser());
            feedback.setComments(handleInfo.getComments());
            feedback.setStatus(handleInfo.getStatus());
            feedbackManager.update(feedback);
            FeedbackInfo feedbackInfo = convert(feedback);
            //发布事件：成功提交后事件
            if (portletFeedbackProperties.isEventPublishEnable()) {
                eventBus.publish(new FeedbackHandleSuccessEvent(feedbackInfo));
            }
            return feedbackInfo;
        } catch (OrderCheckException ie) {
            throw new BusinessException((Messageable) ie);
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCodes.INTERNAL_ERROR);
        }
    }

    protected FeedbackInfo convert(Feedback feedback) {
        return BeanCopier.copy(feedback, FeedbackInfo.class);
    }
}
