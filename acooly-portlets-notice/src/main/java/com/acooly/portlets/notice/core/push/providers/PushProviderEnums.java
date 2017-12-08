/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice.core.push.providers;

import com.acooly.core.utils.enums.Messageable;

/**
 * 消息推送渠道
 * Created by liubin@prosysoft.com on 2017/12/6.
 */
public enum PushProviderEnums implements Messageable {
	JPUSH("JPUSH","极光推送"),
	UMENG("UMENG","友盟推送"),;
	
	private String code;
	
	private String message;
	
	PushProviderEnums (String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	@Override
	public String code () {
		return code;
	}
	
	@Override
	public String message () {
		return message;
	}
}
