/*
 * acooly.cn Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by acooly
 * date:2017-11-23
 *
 */
package com.acooly.portlets.notice.core.service;

import com.acooly.core.common.service.EntityService;
import com.acooly.portlets.notice.core.entity.NoticeRead;

/**
 * 公告消息读取状态 Service接口
 *
 * Date: 2017-11-23 00:53:06
 * @author acooly
 *
 */
public interface NoticeReadService extends EntityService<NoticeRead> {
	
	/**
	 * 阅读广播
	 * @param receiver 接收人
	 */
	void readBroadcast(String receiver,long noticeId);
	
	/**
	 * 根据消息接收人获取消息阅读记录
	 * @param receiver
	 * @return
	 */
	NoticeRead findByReceiver(String receiver);

}
