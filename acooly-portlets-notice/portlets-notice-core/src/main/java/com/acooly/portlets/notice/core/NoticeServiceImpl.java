/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice.core;

import com.acooly.core.utils.Ids;
import com.acooly.core.utils.mapper.JsonMapper;
import com.acooly.core.utils.validate.Validators;
import com.acooly.portlets.notice.PortletNoticeProperties;
import com.acooly.portlets.notice.core.dto.NoticeComponentConstants;
import com.acooly.portlets.notice.core.dto.NoticeMessage;
import com.acooly.portlets.notice.core.entity.NoticeInfo;
import com.acooly.portlets.notice.core.enums.NoticeTypeEnum;
import com.acooly.portlets.notice.core.push.PushResult;
import com.acooly.portlets.notice.core.push.PushService;
import com.acooly.portlets.notice.core.push.providers.PushProviderEnums;
import com.acooly.portlets.notice.core.service.NoticeInfoService;
import com.acooly.portlets.notice.core.service.NoticeReadService;
import com.acooly.portlets.notice.facade.enums.NoticeStatusEnum;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.Date;
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
	private PortletNoticeProperties noticeProperties;
	
	
	@Override
	public void group (NoticeMessage noticeMessage, List<String> targets) {
		
		Validators.assertJSR303 (noticeMessage);
		
		if(StringUtils.isBlank (noticeMessage.getPushNo ())){
			noticeMessage.setPushNo (Ids.getDid ());
		}
		
		saveNoticeInfo (noticeMessage, targets);
		
		//需要进行消息推送
		if (noticeMessage.isPush () && noticeProperties.isPush ()) {
			PushResult pushResult = new PushResult ();
			if(PushProviderEnums.JPUSH.equals (noticeProperties.getPushProvider ())){
				pushResult = jpushService.group (noticeMessage, targets);
			}
			else if(PushProviderEnums.UMENG.equals (noticeProperties.getPushProvider ())){
			
			}
		
			if(pushResult.getSuccess ()){
				noticeInfoService.noticePushSuccess (noticeMessage.getPushNo ());
			}
		}
	}
	
	private void saveNoticeInfo (NoticeMessage noticeMessage, List<String> targets) {
		List<NoticeInfo> noticeInfos = Lists.newArrayList ();
		if(CollectionUtils.isNotEmpty (targets)){
			for (String receiver : targets) {
				if (StringUtils.isNotBlank (receiver)) {
					NoticeInfo noticeInfo = new NoticeInfo ();
					noticeInfo.setReaded (false);
					noticeInfo.setPush (noticeMessage.isPush ());
					noticeInfo.setContent (noticeMessage.getContent ());
					noticeInfo.setSender (noticeMessage.getSender ());
					noticeInfo.setTitle (noticeMessage.getTitle ());
					noticeInfo.setContentType (noticeMessage.getContentType ());
					if(noticeMessage.getContext () != null && !noticeMessage.getContext ().isEmpty ()){
						noticeInfo.setContext (JsonMapper.nonEmptyMapper ().toJson (noticeMessage.getContext ()));
					}
					noticeInfo.setCustomGroup (noticeMessage.getCustomGroup ());
					noticeInfo.setStatus (NoticeStatusEnum.apply);
					noticeInfo.setPushNo (noticeMessage.getPushNo ());
					noticeInfo.setSendTime (new Date ());
					noticeInfo.setReceiver (receiver);
					noticeInfo.setType (NoticeTypeEnum.group);
					noticeInfos.add (noticeInfo);
				}
			}
		}
		else{
			NoticeInfo noticeInfo = new NoticeInfo ();
			noticeInfo.setReaded (false);
			noticeInfo.setPush (noticeMessage.isPush ());
			noticeInfo.setContent (noticeMessage.getContent ());
			noticeInfo.setSender (noticeMessage.getSender ());
			noticeInfo.setTitle (noticeMessage.getTitle ());
			noticeInfo.setContentType (noticeMessage.getContentType ());
			if(noticeMessage.getContext () != null && !noticeMessage.getContext ().isEmpty ()){
				noticeInfo.setContext (JsonMapper.nonEmptyMapper ().toJson (noticeMessage.getContext ()));
			}
			noticeInfo.setCustomGroup (noticeMessage.getCustomGroup ());
			noticeInfo.setStatus (NoticeStatusEnum.apply);
			noticeInfo.setPushNo (noticeMessage.getPushNo ());
			noticeInfo.setSendTime (new Date ());
			noticeInfo.setReceiver (NoticeComponentConstants.BROADCAST_RECEIVER);
			noticeInfo.setType (NoticeTypeEnum.broadcast);
			noticeInfos.add (noticeInfo);
		}
		
		noticeInfoService.saves (noticeInfos);
	}
	
	@Override
	public void broadcast (NoticeMessage noticeMessage) {
		group (noticeMessage,null);
	}
}
