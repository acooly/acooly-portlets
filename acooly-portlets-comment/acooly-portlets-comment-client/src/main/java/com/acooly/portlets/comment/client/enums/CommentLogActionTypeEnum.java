/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by acooly
 * date:2019-01-09
 *
 */
package com.acooly.portlets.comment.client.enums;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.acooly.core.utils.enums.Messageable;

/**
 * 评论日志 CommentLogActionTypeEnum 枚举定义
 * 
 * @author acooly
 * Date: 2019-01-09 00:46:41
 */
public enum CommentLogActionTypeEnum implements Messageable {

	report("report", "举报"),

	thumbsup("thumbsup", "点赞"),

	;

	private final String code;
	private final String message;

	private CommentLogActionTypeEnum(String code, String message) {
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
		for (CommentLogActionTypeEnum type : values()) {
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
	public static CommentLogActionTypeEnum find(String code) {
		for (CommentLogActionTypeEnum status : values()) {
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
	public static List<CommentLogActionTypeEnum> getAll() {
		List<CommentLogActionTypeEnum> list = new ArrayList<CommentLogActionTypeEnum>();
		for (CommentLogActionTypeEnum status : values()) {
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
		for (CommentLogActionTypeEnum status : values()) {
			list.add(status.code());
		}
		return list;
	}

}
