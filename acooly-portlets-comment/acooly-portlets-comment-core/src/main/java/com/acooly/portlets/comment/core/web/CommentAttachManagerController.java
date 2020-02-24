/*
* acooly.cn Inc.
* Copyright (c) 2019 All Rights Reserved.
* create by zhangpu@acooly.cn
* date:2019-05-26
*/
package com.acooly.portlets.comment.core.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acooly.core.common.web.AbstractJsonEntityController;
import com.acooly.portlets.comment.core.entity.CommentAttach;
import com.acooly.portlets.comment.core.manage.CommentAttachManager;
import com.acooly.portlets.comment.client.enums.CommentAttachTypeEnum;

/**
 * 评论附件 管理控制器
 * 
 * @author zhangpu@acooly.cn
 * Date: 2019-05-26 23:54:02
 */
@Controller
@RequestMapping(value = "/manage/portlets/comment/commentAttach")
public class CommentAttachManagerController extends AbstractJsonEntityController<CommentAttach, CommentAttachManager> {
	

	{
		allowMapping = "*";
	}

	@SuppressWarnings("unused")
	@Autowired
	private CommentAttachManager commentAttachService;

	
	@Override
	protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
		model.put("allAttachTypes", CommentAttachTypeEnum.mapping());
	}

}
