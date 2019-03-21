/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-21 00:15
 */
package com.acooly.portlets.alog.core.intergration;

import com.acooly.core.utils.Strings;
import com.acooly.portlets.alog.core.dto.ActionLogInfo;
import com.acooly.portlets.alog.core.service.AlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author zhangpu
 * @date 2019-03-21 00:15
 */
@Slf4j
public class AlogSpringMvcInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private AlogService alogService;

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Alog alog = handlerMethod.getMethodAnnotation(Alog.class);
        if (alog != null && Strings.isNoneBlank(alog.actionKey())) {
            ActionLogInfo actionLogInfo = new ActionLogInfo();
            actionLogInfo.setActionKey(alog.actionKey());
            if (Strings.isNotBlank(alog.actionGroup())) {
                actionLogInfo.setActionGroup(alog.actionGroup());
            }
            if (Strings.isNotBlank(alog.actionName())) {
                actionLogInfo.setActionName(alog.actionName());
            }
            alogService.log(actionLogInfo, request);
        } else {
            alogService.log(request);
        }
    }
}
