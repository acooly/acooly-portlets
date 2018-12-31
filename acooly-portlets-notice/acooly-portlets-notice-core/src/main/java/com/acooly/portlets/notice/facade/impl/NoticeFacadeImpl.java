/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice.facade.impl;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.facade.SingleOrder;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.module.appservice.AppService;
import com.acooly.portlets.notice.core.NoticeService;
import com.acooly.portlets.notice.facade.NoticeFacade;
import com.acooly.portlets.notice.facade.dto.NoticeMessage;
import com.acooly.portlets.notice.facade.enums.PushTypeEnum;
import com.alibaba.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by liubin@prosysoft.com on 2017/12/16.
 */
@Service(version = "1.0")
public class NoticeFacadeImpl implements NoticeFacade {
	
	@Autowired
	private NoticeService noticeService;
	
	
	@Override
	@AppService
	public ResultBase push (SingleOrder<NoticeMessage> pushOrder) {
		ResultBase result = new ResultBase ();
		
		NoticeMessage noticeMessage = pushOrder.getDto ();
		
		com.acooly.portlets.notice.core.dto.NoticeMessage notice = new com.acooly.portlets.notice.core.dto.NoticeMessage ();
		BeanCopier.copy (noticeMessage,notice);
		if(PushTypeEnum.GROUP.equals (noticeMessage.getPushType ())){
			noticeService.group (notice,noticeMessage.getTargets ());
		}
		else{
			noticeService.broadcast (notice);
		}
		return result;
	}
}
