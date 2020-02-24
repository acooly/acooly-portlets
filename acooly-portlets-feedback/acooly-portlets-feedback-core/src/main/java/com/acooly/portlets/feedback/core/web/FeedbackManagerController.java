/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-01-14
 */
package com.acooly.portlets.feedback.core.web;

import com.acooly.core.common.web.AbstractJsonEntityController;
import com.acooly.core.common.web.support.JsonEntityResult;
import com.acooly.module.security.domain.User;
import com.acooly.module.security.utils.ShiroUtils;
import com.acooly.portlets.feedback.client.dto.FeedbackHandleInfo;
import com.acooly.portlets.feedback.client.dto.FeedbackInfo;
import com.acooly.portlets.feedback.client.enums.FeedbackStatusEnum;
import com.acooly.portlets.feedback.client.enums.FeedbackTypeEnum;
import com.acooly.portlets.feedback.core.entity.Feedback;
import com.acooly.portlets.feedback.core.manage.FeedbackManager;
import com.acooly.portlets.feedback.core.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 客户反馈 管理控制器
 *
 * @author zhangpu@acooly.cn
 * Date: 2019-01-14 21:23:31
 */
@Controller
@RequestMapping(value = "/manage/portlets/feedback/feedback")
public class FeedbackManagerController extends AbstractJsonEntityController<Feedback, FeedbackManager> {


    {
        allowMapping = "*";
    }

    @Autowired
    private FeedbackManager feedbackManager;
    @Autowired
    private FeedbackService feedbackService;

    @Override
    protected Map<String, Boolean> getSortMap(HttpServletRequest request) {
        Map<String, Boolean> sortMap = super.getSortMap(request);
        sortMap.put("status", true);
        sortMap.put("id", false);
        return sortMap;
    }


    @RequestMapping(value = "handle")
    @ResponseBody
    public JsonEntityResult<FeedbackInfo> handle(
            HttpServletRequest request, HttpServletResponse response) {
        JsonEntityResult<FeedbackInfo> result = new JsonEntityResult<>();
        try {
            FeedbackHandleInfo feedbackHandleInfo = new FeedbackHandleInfo();
            bindNotValidator(request, feedbackHandleInfo);
            User user = ShiroUtils.getCurrentUser();
            feedbackHandleInfo.setReplyUser(user.getUsername());
            FeedbackInfo feedbackInfo = feedbackService.handle(feedbackHandleInfo);
            result.setEntity(feedbackInfo);
            result.setMessage("反馈处理完成");
        } catch (Exception e) {
            handleException(result, "反馈处理失败", e);
        }
        return result;
    }

    @Override
    protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
        model.put("allTypes", FeedbackTypeEnum.mapping());
        model.put("allStatuss", FeedbackStatusEnum.mapping());
    }

}
