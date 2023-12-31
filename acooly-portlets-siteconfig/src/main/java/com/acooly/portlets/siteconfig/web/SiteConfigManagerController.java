/*
 * acooly.cn Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by acooly
 * date:2017-03-20
 */
package com.acooly.portlets.siteconfig.web;

import com.acooly.core.common.web.AbstractJsonEntityController;
import com.acooly.portlets.siteconfig.entity.SiteConfig;
import com.acooly.portlets.siteconfig.enums.SiteConfigKeyEnum;
import com.acooly.portlets.siteconfig.enums.SiteConfigTypeEnum;
import com.acooly.portlets.siteconfig.service.SiteConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * portlet_site_config 管理控制器
 *
 * @author acooly Date: 2017-03-20 23:36:29
 */
@Controller
@RequestMapping(value = "/manage/module/portlet/siteConfig")
public class SiteConfigManagerController
        extends AbstractJsonEntityController<SiteConfig, SiteConfigService> {

    @SuppressWarnings("unused")
    @Autowired
    private SiteConfigService siteConfigService;

    {
        allowMapping = "*";
    }

    @Override
    protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
        model.put("allTypes", SiteConfigTypeEnum.mapping());
        model.put("allKeys", SiteConfigKeyEnum.mapping());
    }
}
