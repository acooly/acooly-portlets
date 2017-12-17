/*
* acooly.cn Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by acooly
* date:2017-11-23
*/
package com.acooly.portlets.notice.core.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.portlets.notice.core.entity.NoticeRead;
import com.acooly.portlets.notice.core.service.NoticeReadService;
import com.acooly.portlets.notice.core.enums.NoticeReadStatusEnum;

import com.google.common.collect.Maps;

/**
 * 公告消息读取状态 管理控制器
 * 
 * @author acooly
 * Date: 2017-11-23 00:53:06
 */
@Controller
@RequestMapping(value = "/manage/portlets/notice/noticeRead")
public class NoticeReadManagerController extends AbstractJQueryEntityController<NoticeRead, NoticeReadService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private NoticeReadService noticeReadService;

	
	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allStatuss", NoticeReadStatusEnum.mapping());
	}

}
