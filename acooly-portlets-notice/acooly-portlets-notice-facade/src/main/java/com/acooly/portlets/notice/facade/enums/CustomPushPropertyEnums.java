/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice.facade.enums;

import com.acooly.core.utils.enums.Messageable;

/**
 * 可以自定义的推送属性
 * Created by liubin@prosysoft.com on 2017/12/8.
 */
public enum CustomPushPropertyEnums implements Messageable {
	JPUSH_NOTIFICATION("JPUSH_NOTIFICATION","jpush推送报文中的notification字段"),
	JPUSH_OPTIONS("JPUSH_OPTIONS", "JPush实现的Options通知结构参数"),
	;
	
	private CustomPushPropertyEnums (String code, String message){
		this.code = code;
		this.message = message;
	}
	
	private final String code;
	private final String message;
	
	@Override
	public String code () {
		return code;
	}
	
	@Override
	public String message () {
		return message;
	}
}
