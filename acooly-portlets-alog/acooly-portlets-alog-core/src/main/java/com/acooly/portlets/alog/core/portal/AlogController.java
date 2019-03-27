/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-19 00:54
 */
package com.acooly.portlets.alog.core.portal;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.core.utils.Strings;
import com.acooly.portlets.alog.core.AlogProperties;
import com.acooly.portlets.alog.core.dto.ActionLogInfo;
import com.acooly.portlets.alog.core.service.AlogService;
import com.google.common.net.HttpHeaders;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.acooly.portlets.alog.core.AlogProperties.PREFIX;

/**
 * @author zhangpu
 * @date 2019-03-19 00:54
 */
@Slf4j
@ConditionalOnProperty(value = PREFIX + ".enablePortal", matchIfMissing = true)
@RestController
@RequestMapping("/acooly/alog")
public class AlogController extends AbstractJQueryEntityController {

    {
        super.allowMapping = "";
    }

    @Autowired
    private AlogService alogService;

    @Autowired
    private AlogProperties alogProperties;

    @RequestMapping("log")
    @ResponseBody
    public JsonResult log(ActionLogInfo actionLogInfo, HttpServletRequest request, HttpServletResponse response) {
        JsonResult result = new JsonResult();
        try {
            alogService.log(actionLogInfo, request);
        } catch (Exception e) {
            handleException(result, "alog", e);
        }
        // 支持有限跨域访问
        if (Strings.isBlank(alogProperties.getAllowOrigins())) {
            log.debug("alog [禁用] 跨站收集行为日志，未配置扩展收集行为日志的源域名.");
        } else {
            String originHeader = request.getHeader("Origin");
            if(Strings.contains(alogProperties.getAllowOrigins(),originHeader)){
                response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, originHeader);
                response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "POST, GET");
                response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
                log.debug("alog [启用] 跨站收集行为日志，支持域名：{}", alogProperties.getAllowOrigins());
            }
        }
        return result;
    }

}
