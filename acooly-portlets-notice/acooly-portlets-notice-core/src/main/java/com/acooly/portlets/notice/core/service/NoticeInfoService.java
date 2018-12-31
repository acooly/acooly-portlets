/*
 * acooly.cn Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by acooly
 * date:2017-11-23
 *
 */
package com.acooly.portlets.notice.core.service;

import java.util.List;

import com.acooly.core.common.service.EntityService;
import com.acooly.portlets.notice.core.entity.NoticeInfo;

/**
 * 公告消息 Service接口
 *
 * Date: 2017-11-23 00:53:05
 * @author acooly
 *
 */
public interface NoticeInfoService extends EntityService<NoticeInfo> {
	
	
	/**
	 * 同一pushNo的消息推送成功 变更状态
	 * @param pushNo
	 */
	void noticePushSuccess(String pushNo);
	
	/**
	 * 根据自定义组统计消息数
	 * @param customGroup
	 * @return
	 */
	long countUnreadByGroup(String receiver,String customGroup);
	
	
	/**
	 * 全部已读
	 * @param receiver
	 * @param customGroup
	 */
	void readAll(String receiver,String customGroup);
	
	
	/**
	 *
	 * @param receiver
	 * @param customGroup
	 * @return
	 */
	List<Long> getAllBroadcatIds(String receiver,String customGroup);


	NoticeInfo findByPushNoAndReceiver(String pushNo,String receiver);
}
