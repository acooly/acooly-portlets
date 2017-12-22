/*
 * acooly.cn Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by acooly
 * date:2017-11-23
 */
package com.acooly.portlets.notice.core.service.impl;

import com.acooly.core.utils.enums.AbleStatus;
import com.google.common.collect.Lists;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.portlets.notice.core.service.NoticeReadService;
import com.acooly.portlets.notice.core.dao.NoticeReadDao;
import com.acooly.portlets.notice.core.entity.NoticeRead;

import java.util.List;

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
		NoticeRead noticeRead = getEntityDao ().findUniqu ("EQ_receiver", receiver);
		if (noticeRead == null) {
			noticeRead = new NoticeRead ();
			noticeRead.setReceiver (receiver);
			noticeRead.setBroadcastRead (noticeId + "");
			noticeRead.setStatus (AbleStatus.enable);
			getEntityDao ().create (noticeRead);
		} else {
			if (StringUtils.isBlank (noticeRead.getBroadcastRead ())) {
				noticeRead.setBroadcastRead (noticeId + "");
				getEntityDao ().update (noticeRead);
			} else {
				List<String> broadcastReaded = Lists.newArrayList (noticeRead.getBroadcastRead ().split (","));
				if(!broadcastReaded.contains (noticeId+"")){
					noticeRead.setBroadcastRead (noticeRead.getBroadcastRead () + "," + noticeId);
					getEntityDao ().update (noticeRead);
				}
			}
		}
	}
	
	@Override
	public NoticeRead findByReceiver (String receiver) {
		return getEntityDao ().findUniqu ("EQ_receiver", receiver);
	}
	
	@Override
	public void read (String receiver,List<Long> ids) {
		String strIds = list2StringSplitByComma(ids);
		NoticeRead noticeRead = getEntityDao ().findUniqu ("EQ_receiver", receiver);
		if (noticeRead == null) {
			noticeRead = new NoticeRead ();
			noticeRead.setReceiver (receiver);
			noticeRead.setBroadcastRead (strIds);
			noticeRead.setStatus (AbleStatus.enable);
			getEntityDao ().create (noticeRead);
		} else {
			noticeRead.setBroadcastRead (strIds);
			getEntityDao ().update (noticeRead);
		}
	}
	
	
	private String list2StringSplitByComma (List<Long> strs) {
		if (CollectionUtils.isNotEmpty (strs)) {
			String result = "";
			for (Long str : strs) {
				result += str + ",";
			}
			
			return result.substring (0, result.length ()-1);
		} else {
			return "";
		}
	}
}
