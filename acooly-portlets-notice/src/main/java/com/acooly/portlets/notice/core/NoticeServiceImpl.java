/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice.core;

import com.acooly.core.utils.mapper.JsonMapper;
import com.acooly.portlets.notice.PortletNoticeProperties;
import com.acooly.portlets.notice.core.dto.NoticeMessage;
import com.acooly.portlets.notice.core.entity.NoticeInfo;
import com.acooly.portlets.notice.core.enums.NoticeStatusEnum;
import com.acooly.portlets.notice.core.push.PushService;
import com.acooly.portlets.notice.core.push.providers.PushProviderEnums;
import com.acooly.portlets.notice.core.service.NoticeInfoService;
import com.acooly.portlets.notice.core.service.NoticeReadService;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by liubin@prosysoft.com on 2017/12/7.
 */
@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {
	
	@Resource(name = "jpushPushService")
	private PushService jpushService;
	
	@Autowired
	private NoticeInfoService noticeInfoService;
	
	@Autowired
	private NoticeReadService noticeReadService;
	
	@Autowired
	private PortletNoticeProperties noticeProperties;
	
	
	@Override
	public void group (NoticeMessage noticeMessage, List<String> targets) {
		
		Assert.notEmpty (targets, "消息接收人不能为空");
		
		List<NoticeInfo> noticeInfos = Lists.newArrayList ();
		for (String receiver : targets) {
			if (StringUtils.isNotBlank (receiver)) {
				NoticeInfo noticeInfo = new NoticeInfo ();
				noticeInfo.setReaded (false);
				noticeInfo.setContent (noticeMessage.getContent ());
				noticeInfo.setSender (noticeMessage.getSender ());
				noticeInfo.setTitle (noticeMessage.getTitle ());
				noticeInfo.setContentType (noticeMessage.getContentType ());
				noticeInfo.setContext (JsonMapper.nonEmptyMapper ().toJson (noticeMessage.getContext ()));
				noticeInfo.setCustomGroup (noticeMessage.getCustomGroup ());
				noticeInfo.setStatus (NoticeStatusEnum.apply);
				noticeInfos.add (noticeInfo);
			}
		}
		
		noticeInfoService.saves (noticeInfos);
		
		//需要进行消息推送
		if (noticeMessage.isPush ()) {
			if(PushProviderEnums.JPUSH.equals (noticeProperties.getPushProvider ())){
				jpushService.group (noticeMessage,targets);
			}
			else if(PushProviderEnums.UMENG.equals (noticeProperties.getPushProvider ())){
			
			}
		}
	}
	
	
	@Override
	public void broadcast (NoticeMessage noticeMessage) {
	
	
	}
}
