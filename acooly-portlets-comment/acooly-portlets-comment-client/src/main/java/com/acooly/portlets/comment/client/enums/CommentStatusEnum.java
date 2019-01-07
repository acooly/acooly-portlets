/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by acooly
 * date:2019-01-06
 *
 */
package com.acooly.portlets.comment.client.enums;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.acooly.core.utils.enums.Messageable;

/**
 * p_comment CommentStatusEnum 枚举定义
 * 
 * @author acooly
 * Date: 2019-01-06 19:37:26
 */
public enum CommentStatusEnum implements Messageable {

	enable_publish("enable_publish", "发布"),

	enable_report("enable_report", "举报"),

	enable_review("enable_review", "管理编辑"),

	disabled("disabled", "禁用"),

	;

	private final String code;
	private final String message;

	private CommentStatusEnum(String code, String message) {
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
		for (CommentStatusEnum type : values()) {
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
	public static CommentStatusEnum find(String code) {
		for (CommentStatusEnum status : values()) {
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
	public static List<CommentStatusEnum> getAll() {
		List<CommentStatusEnum> list = new ArrayList<CommentStatusEnum>();
		for (CommentStatusEnum status : values()) {
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
		for (CommentStatusEnum status : values()) {
			list.add(status.code());
		}
		return list;
	}

}
