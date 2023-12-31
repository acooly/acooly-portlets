/*
 * acooly.cn Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by acooly
 * date:2017-11-23
 *
 */
package com.acooly.portlets.notice.core.enums;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.acooly.core.utils.enums.Messageable;

/**
 * 公告消息读取状态 NoticeReadStatusEnum 枚举定义
 * 
 * @author acooly
 * Date: 2017-11-23 00:53:06
 */
public enum NoticeReadStatusEnum implements Messageable {

	enable("enable", "有效"),

	disable("disable", "无效"),

	;

	private final String code;
	private final String message;

	private NoticeReadStatusEnum(String code, String message) {
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
		for (NoticeReadStatusEnum type : values()) {
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
	public static NoticeReadStatusEnum find(String code) {
		for (NoticeReadStatusEnum status : values()) {
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
	public static List<NoticeReadStatusEnum> getAll() {
		List<NoticeReadStatusEnum> list = new ArrayList<NoticeReadStatusEnum>();
		for (NoticeReadStatusEnum status : values()) {
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
		for (NoticeReadStatusEnum status : values()) {
			list.add(status.code());
		}
		return list;
	}

}
