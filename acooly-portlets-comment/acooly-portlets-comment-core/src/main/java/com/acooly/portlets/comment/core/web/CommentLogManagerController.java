/*
* acooly.cn Inc.
* Copyright (c) 2019 All Rights Reserved.
* create by acooly
* date:2019-01-09
*/
package com.acooly.portlets.comment.core.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJsonEntityController;
import com.acooly.portlets.comment.core.entity.CommentLog;
import com.acooly.portlets.comment.core.manage.CommentLogManager;
import com.acooly.portlets.comment.client.enums.CommentLogActionTypeEnum;

/**
 * 评论日志 管理控制器
 * 
 * @author acooly
 * Date: 2019-01-09 00:46:41
 */
@Controller
@RequestMapping(value = "/manage/portlets/comment/commentLog")
public class CommentLogManagerController extends AbstractJsonEntityController<CommentLog, CommentLogManager> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private CommentLogManager commentLogService;

	
	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allActionTypes", CommentLogActionTypeEnum.mapping());
	}

}
