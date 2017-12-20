/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice.facade.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by liubin@prosysoft.com on 2017/12/5.
 */
@Getter
@Setter
@ToString
public class PageableNoticeInfo implements Serializable{
	
	/**
	 * 消息id
	 */
	private Long id;
	
	/**
	 * 消息标题
	 */
	private String title;
	
	/**
	 * 消息内容
	 */
	private String content;
	
	/**
	 * 发送时间
	 */
	private String sendTime;
	
	/**
	 * 已读
	 */
	private Boolean readed;
}
