/*
 * www.acooly.cn Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2017-11-23 01:48 创建
 */
package com.acooly.portlets.notice.core.push;

import com.acooly.portlets.notice.core.dto.NoticeMessage;
import com.acooly.portlets.notice.core.push.providers.PushProviderEnums;

import java.util.List;

/**
 * @author zhangpu 2017-11-23 01:48
 */
public interface PushService {
	
	/**
	 * 群发
	 *
	 * @param noticeMessage 消息
	 * @param targets 发送目标，一般使用唯一用户标识
	 */
	void group (NoticeMessage noticeMessage, List<String> targets);
	
	/**
	 * 广播
	 *
	 * @param noticeMessage
	 */
	void broadcast (NoticeMessage noticeMessage);
	
	
	/**
	 * 获取消息推送渠道方
	 * @return
	 */
	PushProviderEnums getProvider();


}
