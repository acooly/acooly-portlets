/*
 * acooly.cn Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by acooly
 * date:2017-11-23
 */
package com.acooly.portlets.notice.core.service.impl;

import com.acooly.portlets.notice.facade.enums.NoticeStatusEnum;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.portlets.notice.core.service.NoticeInfoService;
import com.acooly.portlets.notice.core.dao.NoticeInfoDao;
import com.acooly.portlets.notice.core.entity.NoticeInfo;

import java.util.List;

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
	
	@Override
	public long countByGroup (String customGroup) {
		return getEntityDao ().coutByGroup (customGroup);
	}
	
	@Override
	public void readAll (String receiver, String customGroup) {
		if(StringUtils.isNotBlank (customGroup)){
			getEntityDao ().updateAllNoticeReadedByGroup (receiver, customGroup);
		}
		else{
			getEntityDao ().updateAllNoticeReaded (receiver);
		}
	}
	
	@Override
	public List<Long> getAllBroadcatIds (String receiver, String customGroup) {
		if(StringUtils.isNotBlank (customGroup)){
			return getEntityDao ().getAllBroadcatIdsByGroup (customGroup);
		}
		else{
			return getEntityDao ().getAllBroadcatIds ();
		}
	}
}
