/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

/**
 * create by zhangpu date:2015年11月4日
 */
package com.acooly.portlets.notice.core.push.providers.jpush;

import com.acooly.core.common.boot.EnvironmentHolder;
import com.acooly.core.utils.Collections3;
import com.acooly.portlets.notice.PortletNoticeProperties;
import com.acooly.portlets.notice.core.dto.CustomPushPropertyEnums;
import com.acooly.portlets.notice.core.dto.NoticeComponentConstants;
import com.acooly.portlets.notice.core.dto.NoticeMessage;
import com.acooly.portlets.notice.core.push.PushService;
import com.acooly.portlets.notice.core.push.providers.PushProviderEnums;
import com.acooly.portlets.notice.core.push.providers.jpush.dto.JPushMessage;
import com.acooly.portlets.notice.core.push.providers.jpush.dto.JPushNotification;
import com.acooly.portlets.notice.core.push.providers.jpush.dto.JPushOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 系统通知 JPush实现
 *
 * @author zhangpu
 * @date 2015年11月4日
 */
@Service("jpushPushService")
public class JPushPushServiceImpl implements PushService {
	
	private static final Logger logger = LoggerFactory.getLogger (JPushPushServiceImpl.class);
	
	@Resource
	protected JPushSendService jPushSendService;
	
	@Autowired
	private PortletNoticeProperties noticeProperties;
	
	@Override
	public void group (NoticeMessage noticeMessage, List<String> targets) {
		try {
			JPushOrder order = new JPushOrder ();
			// audience
			if (Collections3.isNotEmpty (targets)) {
				order.getAudience ().setAlias (targets);
			}
			
			String title = noticeMessage.getTitle ();
			String content = noticeMessage.getContent ();
			Map<String, Object> extras = noticeMessage.getContext ();
			// message
			JPushMessage jpushMessage = new JPushMessage ();
			jpushMessage.setTitle (noticeMessage.getTitle ());
			jpushMessage.setMsgContent (noticeMessage.getContent ());
			Map<String, Object> context = noticeMessage.getContext ();
			if (context != null && context.size () > 0) {
				jpushMessage.setExtras (context);
			}
			order.setMessage (jpushMessage);
			
			// notification && options
			JPushNotification jPushNotification = new JPushNotification ();
			
			JPushNotification.Android android = new JPushNotification.Android ();
			android.setAlert (content);
			android.setTitle (title);
			android.setExtras (extras);
			jPushNotification.setAndroid (android);
			
			JPushNotification.IOS ios = new JPushNotification.IOS ();
			ios.setAlert (content);
			ios.setExtras (extras);
			jPushNotification.setIos (ios);
			
			order.setNotification (jPushNotification);
			
			JPushOptions jPushOptions =
					new JPushOptions (noticeProperties.getTimeToLive (), isOnline());
			order.setOptions (jPushOptions);
			
			Map<CustomPushPropertyEnums, Object> properties = noticeMessage.getProperties ();
			if (properties != null && !properties.isEmpty ()) {
				JPushNotification jnotification =
						(JPushNotification) properties.get (CustomPushPropertyEnums.JPUSH_NOTIFICATION);
				if (jnotification != null) {
					order.setNotification (jnotification);
				}
				JPushOptions joptions = (JPushOptions) properties.get (CustomPushPropertyEnums.JPUSH_NOTIFICATION);
				if (joptions != null) {
					order.setOptions (joptions);
				}
			}
			
			JPushResult result = jPushSendService.request (order);
			if (result.getHttpStatus () != 200) {
				throw new RuntimeException (result.getCode () + ":" + result.getMessage ());
			}
			logger.info ("JPush推送成功: request:{}, result:{}", order, result);
		} catch (Exception e) {
			logger.error ("JPush通知发送失败", e);
			throw new RuntimeException ("JPush通知发送失败:" + e.getMessage ());
		}
	}
	
	@Override
	public void broadcast (NoticeMessage noticeMessage) {
		group (noticeMessage, null);
	}
	
	@Override
	public PushProviderEnums getProvider () {
		return PushProviderEnums.JPUSH;
	}
	
	private boolean isOnline(){
		return NoticeComponentConstants.ONLINE_PROFILE.equals (EnvironmentHolder.get ().getActiveProfiles ()[0]);
	}
	
	
}
