/*
 * acooly.cn Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by acooly
 * date:2017-11-23
 */
package com.acooly.portlets.notice.core.service.impl;

import com.acooly.core.utils.enums.AbleStatus;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.portlets.notice.core.service.NoticeReadService;
import com.acooly.portlets.notice.core.dao.NoticeReadDao;
import com.acooly.portlets.notice.core.entity.NoticeRead;

/**
 * 公告消息读取状态 Service实现
 * <p>
 * Date: 2017-11-23 00:53:06
 *
 * @author acooly
 */
@Service("noticeReadService")
public class NoticeReadServiceImpl extends EntityServiceImpl<NoticeRead, NoticeReadDao> implements NoticeReadService {
	
	@Override
	public void readBroadcast (String receiver, long noticeId) {
		NoticeRead noticeRead = getEntityDao ().findUniqu ("receiver", receiver);
		if (noticeRead == null) {
			noticeRead = new NoticeRead ();
			noticeRead.setReceiver (receiver);
			noticeRead.setMessageRead (noticeId + "");
			noticeRead.setStatus (AbleStatus.enable);
			getEntityDao ().create (noticeRead);
		} else {
			if (StringUtils.isBlank (noticeRead.getMessageRead ())) {
				noticeRead.setMessageRead (noticeId + "");
			} else {
				noticeRead.setMessageRead (noticeRead.getMessageRead () + "," + noticeId);
			}
			
			getEntityDao ().update (noticeRead);
		}
	}
	
	@Override
	public NoticeRead findByReceiver (String receiver) {
		return getEntityDao ().findUniqu ("receiver", receiver);
	}
}
