/**
 * openapi-framework
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-26 21:22
 */
package com.acooly.portlets.alog.openapi.listener;

import com.acooly.openapi.framework.common.annotation.OpenApiListener;
import com.acooly.openapi.framework.common.context.ApiContext;
import com.acooly.openapi.framework.common.context.ApiContextHolder;
import com.acooly.openapi.framework.common.event.dto.AfterServiceExecuteEvent;
import com.acooly.openapi.framework.core.listener.AbstractListener;
import com.acooly.portlets.alog.core.AlogProperties;
import com.acooly.portlets.alog.core.dto.ActionLogInfo;
import com.acooly.portlets.alog.core.service.AlogService;
import com.google.common.net.HttpHeaders;
import eu.bitwalker.useragentutils.BrowserType;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

import javax.servlet.http.HttpServletRequest;

import static com.acooly.portlets.alog.core.AlogProperties.PREFIX;

/**
 * @author zhangpu
 * @date 2019-03-26 21:22
 */
@Slf4j
@ConditionalOnProperty(value = PREFIX + ".openApiEnable", matchIfMissing = true)
@OpenApiListener(global = true)
public class AlogApiListener extends AbstractListener<AfterServiceExecuteEvent> {

    @Autowired
    private AlogService alogService;

    @Autowired
    private AlogProperties alogProperties;

    @Override
    public void onOpenApiEvent(AfterServiceExecuteEvent event) {
        try {
            HttpServletRequest request = ApiContextHolder.getApiContext().getOrignalRequest();
            UserAgent userAgent = UserAgent.parseUserAgentString(request.getHeader(HttpHeaders.USER_AGENT));
            if (alogProperties.getOpenApiOnlyApp() && userAgent.getBrowser().getBrowserType() != BrowserType.APP) {
                // 不是来之移动App的请求，忽略
                return;
            }
            ApiContext apiContext = ApiContextHolder.getApiContext();
            ActionLogInfo actionLogInfo = new ActionLogInfo();
            actionLogInfo.setActionKey(apiContext.getServiceName());
            actionLogInfo.setActionName(apiContext.getOpenApiService().desc());
            alogService.log(actionLogInfo, ApiContextHolder.getApiContext().getOrignalRequest());
        } catch (Exception e) {
            log.warn("OpenApi-Alog服务后置事件监听处理失败：{}", e.getMessage());
        }
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE - 1;
    }
}
