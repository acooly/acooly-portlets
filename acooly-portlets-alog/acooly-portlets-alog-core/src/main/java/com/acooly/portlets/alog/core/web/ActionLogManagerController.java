/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-01-10
 */
package com.acooly.portlets.alog.core.web;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.portlets.alog.core.entity.ActionLog;
import com.acooly.portlets.alog.core.enums.ActionChannel;
import com.acooly.portlets.alog.core.enums.ActionOS;
import com.acooly.portlets.alog.core.service.ActionLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * 访问日志 管理控制器
 *
 * @author zhangpu@acooly.cn
 * Date: 2019-01-10 18:10:55
 */
@Controller
@RequestMapping(value = "/manage/portlets/alog/actionLog")
public class ActionLogManagerController extends AbstractJQueryEntityController<ActionLog, ActionLogService> {


    {
        allowMapping = "*";
    }

    @SuppressWarnings("unused")
    @Autowired
    private ActionLogService actionLogService;


    @Override
    protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
        model.put("allChannels", ActionChannel.mapping());
        model.put("allOss", ActionOS.mapping());
    }

}
