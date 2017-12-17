/*
 * acooly.cn Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by acooly
 * date:2017-11-23
 */
package com.acooly.portlets.notice.core.service.impl;

import com.acooly.portlets.notice.facade.enums.NoticeStatusEnum;
import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.portlets.notice.core.service.NoticeInfoService;
import com.acooly.portlets.notice.core.dao.NoticeInfoDao;
import com.acooly.portlets.notice.core.entity.NoticeInfo;

/**
 * 公告消息 Service实现
 *
 * Date: 2017-11-23 00:53:06
 *
 * @author acooly
 *
 */
@Service("noticeInfoService")
public class NoticeInfoServiceImpl extends EntityServiceImpl<NoticeInfo, NoticeInfoDao> implements NoticeInfoService {
	
	@Override
	public void noticePushSuccess (String pushNo) {
		getEntityDao ().updateStatusByPushNo (NoticeStatusEnum.finish, pushNo);
	}
}
