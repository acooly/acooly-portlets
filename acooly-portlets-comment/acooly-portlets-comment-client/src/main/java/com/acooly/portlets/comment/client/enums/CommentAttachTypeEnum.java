/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-05-26
 *
 */
package com.acooly.portlets.comment.client.enums;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.acooly.core.utils.enums.Messageable;

/**
 * 评论附件 CommentAttachTypeEnum 枚举定义
 * 
 * @author zhangpu@acooly.cn
 * Date: 2019-05-26 23:54:02
 */
public enum CommentAttachTypeEnum implements Messageable {

	photo("photo", "图片"),
	video("video", "视频"),
	other("other", "其他"),
	;

	private final String code;
	private final String message;

	private CommentAttachTypeEnum(String code, String message) {
		this.code = code;
		this.message = message;
	}


	public String getCode() {
		return code;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String code() {
		return code;
	}

	@Override
	public String message() {
		return message;
	}

	public static Map<String, String> mapping() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		for (CommentAttachTypeEnum type : values()) {
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
	public static CommentAttachTypeEnum find(String code) {
		for (CommentAttachTypeEnum status : values()) {
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
	public static List<CommentAttachTypeEnum> getAll() {
		List<CommentAttachTypeEnum> list = new ArrayList<CommentAttachTypeEnum>();
		for (CommentAttachTypeEnum status : values()) {
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
		for (CommentAttachTypeEnum status : values()) {
			list.add(status.code());
		}
		return list;
	}

}
