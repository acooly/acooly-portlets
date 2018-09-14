/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice.core.push.providers.umeng;

import com.acooly.core.common.boot.EnvironmentHolder;
import com.acooly.portlets.notice.PortletNoticeProperties;
import com.acooly.portlets.notice.core.dto.NoticeComponentConstants;
import com.acooly.portlets.notice.core.dto.NoticeMessage;
import com.acooly.portlets.notice.core.push.PushResult;
import com.acooly.portlets.notice.core.push.PushService;
import com.acooly.portlets.notice.core.push.providers.PushProviderEnums;
import com.acooly.portlets.notice.core.push.providers.umeng.android.AndroidBroadcast;
import com.acooly.portlets.notice.core.push.providers.umeng.android.AndroidCustomizedcast;
import com.acooly.portlets.notice.core.push.providers.umeng.ios.IOSBroadcast;
import com.acooly.portlets.notice.core.push.providers.umeng.ios.IOSCustomizedcast;
import com.acooly.portlets.notice.facade.enums.DeviceTypeEnum;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;

/**
 * Created by liubin@prosysoft.com on 2017/12/18.
 */
@Service("uMengPushService")
public class UMengPushServiceImpl implements PushService {
	
	private final String ALIAS_TYPE="USER_ID";
	
	@Autowired
	private PortletNoticeProperties noticeProperties;
	
	
	@Override
	public PushResult group (NoticeMessage noticeMessage, List<String> targets) {
		PushResult pushResult = new PushResult ();
		try {
			PushClient pushClient = new PushClient ();
			
			if (DeviceTypeEnum.ANDROID.equals (noticeMessage.getDeviceType ())) {
				AndroidCustomizedcast androidCustomizedcast = contructAndroidCustomcast (noticeMessage, targets);
				pushClient.send (androidCustomizedcast);
			} else if(DeviceTypeEnum.IOS.equals(noticeMessage.getDeviceType())){
				IOSCustomizedcast iosCustomizedcast = constructIosCustomcast (noticeMessage, targets);
				pushClient.send (iosCustomizedcast);
			}
			else{
				AndroidCustomizedcast androidCustomizedcast = contructAndroidCustomcast (noticeMessage, targets);
				pushClient.send (androidCustomizedcast);

				IOSCustomizedcast iosCustomizedcast = constructIosCustomcast (noticeMessage, targets);
				pushClient.send (iosCustomizedcast);
			}
			
			pushResult.setSuccess (true);
		} catch (IllegalArgumentException ie) {
			pushResult.setMessage (ie.getMessage ());
			pushResult.setSuccess (false);
			
		} catch (Exception e) {
			pushResult.setSuccess (false);
			pushResult.setMessage (e.getMessage ());
		}
		
		return pushResult;
	}
	
	@Override
	public PushResult broadcast (NoticeMessage noticeMessage) {
		PushResult pushResult = new PushResult ();
		try {
			PushClient pushClient = new PushClient ();
			//安卓广播
			AndroidBroadcast broadcast = contructAndroidBroadcast (noticeMessage);
			pushClient.send (broadcast);
			//ios广播
			IOSBroadcast iosBroadcast = constructIosBroadcast (noticeMessage);
			pushClient.send (iosBroadcast);
			pushResult.setSuccess (true);
		} catch (IllegalArgumentException ie) {
			pushResult.setMessage (ie.getMessage ());
			pushResult.setSuccess (false);
		} catch (Exception e) {
			pushResult.setSuccess (false);
			pushResult.setMessage (e.getMessage ());
		}
		
		return pushResult;
	}
	
	@Override
	public PushProviderEnums getProvider () {
		return PushProviderEnums.UMENG;
	}
	
	private boolean isOnline () {
		return NoticeComponentConstants.ONLINE_PROFILE.equals (EnvironmentHolder.get ().getActiveProfiles ()[0]);
	}
	
	private AndroidCustomizedcast contructAndroidCustomcast (NoticeMessage noticeMessage, List<String> targets)
			throws Exception {
		AndroidCustomizedcast androidCustomizedcast = new AndroidCustomizedcast (
				noticeProperties.getUmeng ().getAndroidAppKey (),
				noticeProperties.getUmeng ().getAndroidMasterSecret ());
		
		androidCustomizedcast.setAlias (list2StringSplitByComma (targets), ALIAS_TYPE);
		androidCustomizedcast.setTicker (noticeMessage.getTitle ());
		androidCustomizedcast.setTitle (noticeMessage.getTitle ());
		androidCustomizedcast.setText (noticeMessage.getContent ());
		androidCustomizedcast.setDisplayType (AndroidNotification.DisplayType.NOTIFICATION);
		androidCustomizedcast.goAppAfterOpen ();
		if (noticeMessage.getContext () != null) {
			for (Map.Entry<String, Object> entry : noticeMessage.getContext ().entrySet ()) {
				androidCustomizedcast.setExtraField (entry.getKey (), entry.getValue ().toString ());
			}
		}
		//判断开启线上还是线下
		if (isOnline ()) {
			androidCustomizedcast.setProductionMode ();
		} else {
			androidCustomizedcast.setTestMode ();
		}
		return androidCustomizedcast;
	}
	
	private AndroidBroadcast contructAndroidBroadcast (NoticeMessage noticeMessage)
			throws Exception {
		AndroidBroadcast broadcast = new AndroidBroadcast (
				noticeProperties.getUmeng ().getAndroidAppKey (),
				noticeProperties.getUmeng ().getAndroidMasterSecret ());
		
		broadcast.setTicker (noticeMessage.getTitle ());
		broadcast.setTitle (noticeMessage.getTitle ());
		broadcast.setText (noticeMessage.getContent ());
		broadcast.setDisplayType (AndroidNotification.DisplayType.NOTIFICATION);
		broadcast.goAppAfterOpen ();
		if (noticeMessage.getContext () != null) {
			for (Map.Entry<String, Object> entry : noticeMessage.getContext ().entrySet ()) {
				broadcast.setExtraField (entry.getKey (), entry.getValue ().toString ());
			}
		}
		//判断开启线上还是线下
		if (isOnline ()) {
			broadcast.setProductionMode ();
		} else {
			broadcast.setTestMode ();
		}
		return broadcast;
	}
	
	private IOSCustomizedcast constructIosCustomcast (NoticeMessage noticeMessage, List<String> targets)
			throws Exception {
		IOSCustomizedcast iosCustomizedcast = new IOSCustomizedcast (
				noticeProperties.getUmeng ().getIosAppKey (),
				noticeProperties.getUmeng ().getIosMasterSecret ());
		if (noticeMessage.getContext () != null) {
			for (Map.Entry<String, Object> entry : noticeMessage.getContext ().entrySet ()) {
				iosCustomizedcast.setCustomizedField (entry.getKey (), entry.getValue ().toString ());
			}
		}
		//                iosCustomizedcast.setAlert(umengNotifyDto.getContent());
		iosCustomizedcast.setAlias (list2StringSplitByComma (targets), ALIAS_TYPE);
		//判断开启线上还是线下
		if (isOnline ()) {
			iosCustomizedcast.setProductionMode ();
		} else {
			iosCustomizedcast.setTestMode ();
		}
		
		iosCustomizedcast.setBadge (1);
		iosCustomizedcast.setSound ("default");
		iosCustomizedcast.setTitle(noticeMessage.getTitle());
		iosCustomizedcast.setBody (noticeMessage.getContent ());
		return iosCustomizedcast;
	}
	
	private IOSBroadcast constructIosBroadcast (NoticeMessage noticeMessage)
			throws Exception {
		IOSBroadcast broadcast = new IOSBroadcast (
				noticeProperties.getUmeng ().getIosAppKey (),
				noticeProperties.getUmeng ().getIosMasterSecret ());
		if (noticeMessage.getContext () != null) {
			for (Map.Entry<String, Object> entry : noticeMessage.getContext ().entrySet ()) {
				broadcast.setCustomizedField (entry.getKey (), entry.getValue ().toString ());
			}
		}
		//判断开启线上还是线下
		if (isOnline ()) {
			broadcast.setProductionMode ();
		} else {
			broadcast.setTestMode ();
		}
		
		broadcast.setBadge (1);
		broadcast.setSound ("default");
		broadcast.setBody (noticeMessage.getContent ());
		broadcast.setTitle(noticeMessage.getTitle());
		return broadcast;
	}
	
	
	private String list2StringSplitByComma (List<String> strs) {
		if (CollectionUtils.isNotEmpty (strs)) {
			String result = "";
			for (String str : strs) {
				result += str + ",";
			}
			
			return result.substring (0, result.length ()-1);
		} else {
			return "";
		}
	}
}
