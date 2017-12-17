/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice.core.push;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import java.io.Serializable;

/**
 * Created by liubin@prosysoft.com on 2017/12/12.
 */
@ToString
public class PushResult implements Serializable {
	
	/**
	 * 是否推送成功
	 */
	private Boolean success;
	
	/**
	 * 错误码
	 */
	private String code;
	
	/**
	 * 错误消息
	 */
	private String message;
	
	private String sendNo;
	
	private String msgId;
	
	
	public String getCode () {
		return code;
	}
	
	public void setCode (String code) {
		this.code = code;
	}
	
	public String getMessage () {
		return message;
	}
	
	public void setMessage (String message) {
		this.message = message;
	}
	
	public String getSendNo () {
		return sendNo;
	}
	
	public void setSendNo (String sendNo) {
		this.sendNo = sendNo;
	}
	
	public String getMsgId () {
		return msgId;
	}
	
	public void setMsgId (String msgId) {
		this.msgId = msgId;
	}
	
	public Boolean getSuccess () {
		return success;
	}
	
	public void setSuccess (Boolean success) {
		this.success = success;
	}
}
