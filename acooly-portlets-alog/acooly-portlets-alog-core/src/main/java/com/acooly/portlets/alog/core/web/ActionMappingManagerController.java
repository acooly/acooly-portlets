/*
* acooly.cn Inc.
* Copyright (c) 2019 All Rights Reserved.
* create by zhangpu@acooly.cn
* date:2019-01-10
*/
package com.acooly.portlets.alog.core.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.portlets.alog.core.entity.ActionMapping;
import com.acooly.portlets.alog.core.service.ActionMappingService;
import com.acooly.portlets.alog.core.enums.ActionMappingTypeEnum;

/**
 * 访问映射 管理控制器
 * 
 * @author zhangpu@acooly.cn
 * Date: 2019-01-10 18:10:55
 */
@Controller
@RequestMapping(value = "/manage/portlets/alog/actionMapping")
public class ActionMappingManagerController extends AbstractJQueryEntityController<ActionMapping, ActionMappingService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private ActionMappingService actionMappingService;

	
	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allMappingTypes", ActionMappingTypeEnum.mapping());
	}

}
