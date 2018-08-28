/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */
package com.acooly.portlets.notice.facade.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 公告消息 NoticeInfoContentTypeEnum 枚举定义
 * 
 * @author acooly
 * Date: 2017-11-23 00:53:06
 */
public enum NoticeContentTypeEnum implements Messageable {

	MESSAGE("MESSAGE", "消息"),

	NOTIFACTION("NOTIFACTION", "通知"),
	;

	private final String code;
	private final String message;

	private NoticeContentTypeEnum (String code, String message) {
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	public String code() {
		return code;
	}

	public String message() {
		return message;
	}

	public static Map<String, String> mapping() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (NoticeContentTypeEnum type : values()) {
			map.put(type.getCode(), type.getMessage());
		}
		return map;
	}

	/**
	 * 通过枚举值码查找枚举值。
	 * 
	 * @param code
	 *            查找枚举值的枚举值码。
	 * @return 枚举值码对应的枚举值。
	 * @throws IllegalArgumentException
	 *             如果 code 没有对应的 Status 。
	 */
	public static NoticeContentTypeEnum find(String code) {
		for (NoticeContentTypeEnum status : values()) {
			if (status.getCode().equals(code)) {
				return status;
			}
		}
		return null;
	}

	/**
	 * 获取全部枚举值。
	 * 
	 * @return 全部枚举值。
	 */
	public static List<NoticeContentTypeEnum> getAll() {
		List<NoticeContentTypeEnum> list = new ArrayList<NoticeContentTypeEnum>();
		for (NoticeContentTypeEnum status : values()) {
			list.add(status);
		}
		return list;
	}

	/**
	 * 获取全部枚举值码。
	 * 
	 * @return 全部枚举值码。
	 */
	public static List<String> getAllCode() {
		List<String> list = new ArrayList<String>();
		for (NoticeContentTypeEnum status : values()) {
			list.add(status.code());
		}
		return list;
	}

}
