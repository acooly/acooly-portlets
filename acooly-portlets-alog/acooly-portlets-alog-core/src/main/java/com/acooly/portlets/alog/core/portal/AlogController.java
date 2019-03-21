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
import com.acooly.portlets.alog.core.dto.ActionLogInfo;
import com.acooly.portlets.alog.core.service.AlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping("log")
    @ResponseBody
    public JsonResult log(ActionLogInfo actionLogInfo, HttpServletRequest request) {
        JsonResult result = new JsonResult();
        try {
            alogService.log(actionLogInfo, request);
        } catch (Exception e) {
            handleException(result, "alog", e);
        }
        return result;
    }

}
