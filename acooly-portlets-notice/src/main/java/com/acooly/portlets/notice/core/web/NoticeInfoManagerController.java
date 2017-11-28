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
import com.acooly.portlets.notice.core.entity.NoticeInfo;
import com.acooly.portlets.notice.core.service.NoticeInfoService;
import com.acooly.portlets.notice.core.enums.NoticeContentTypeEnum;
import com.acooly.portlets.notice.core.enums.NoticeStatusEnum;
import com.acooly.portlets.notice.core.enums.NoticeTypeEnum;

/**
 * 公告消息 管理控制器
 * 
 * @author acooly
 * Date: 2017-11-23 00:53:05
 */
@Controller
@RequestMapping(value = "/manage/portlets/notice/noticeInfo")
public class NoticeInfoManagerController extends AbstractJQueryEntityController<NoticeInfo, NoticeInfoService> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private NoticeInfoService noticeInfoService;

	
	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allContentTypes", NoticeContentTypeEnum.mapping());
		model.put("allStatuss", NoticeStatusEnum.mapping());
		model.put("allTypes", NoticeTypeEnum.mapping());
	}

}
