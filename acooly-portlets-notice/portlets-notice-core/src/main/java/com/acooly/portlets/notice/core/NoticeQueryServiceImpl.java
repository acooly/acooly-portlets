/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice.core;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.utils.enums.AbleStatus;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.portlets.notice.core.dto.NoticeComponentConstants;
import com.acooly.portlets.notice.core.entity.NoticeInfo;
import com.acooly.portlets.notice.core.entity.NoticeRead;
import com.acooly.portlets.notice.core.service.NoticeInfoService;
import com.acooly.portlets.notice.core.service.NoticeReadService;
import com.acooly.portlets.notice.facade.dto.PageableNoticeInfo;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liubin@prosysoft.com on 2017/12/5.
 */
@Service
public class NoticeQueryServiceImpl implements NoticeQueryService {
	
	@Autowired
	private NoticeInfoService noticeInfoService;
	
	@Autowired
	private NoticeReadService noticeReadService;
	
	@Override
	public PageInfo<PageableNoticeInfo> pageQuery (String receiver, PageInfo<NoticeInfo> pager,
	                                               Map<String, Object> params,
	                                               Map<String, Boolean> orderBy) {
		Assert.hasText (receiver, "消息接收人不能为空");
		
		if (orderBy == null || orderBy.isEmpty ()) {
			orderBy = new HashMap<> ();
			orderBy.put ("createTime", false);
		}
		
		params.put ("EQ_receiver", receiver);
		
		PageInfo<NoticeInfo> pageResult = noticeInfoService.query (pager, params, orderBy);
		
		NoticeRead noticeRead = noticeReadService.findByReceiver (receiver);
		if(noticeRead == null){
			noticeRead = new NoticeRead ();
			noticeRead.setStatus (AbleStatus.enable);
			noticeRead.setReceiver (receiver);
			noticeReadService.save (noticeRead);
		}
		
		List<String> readList = Lists.newArrayList ();
		if (StringUtils.isNoneBlank (noticeRead.getBroadcastRead ())) {
			readList = Lists.newArrayList (noticeRead.getBroadcastRead ().split (","));
		}
		
		PageInfo<PageableNoticeInfo> pageInfo = new PageInfo<> ();
		BeanCopier.copy (pageResult, pageInfo, "pageResults");
		pageInfo.setPageResults (Lists.newArrayList ());
		
		for(NoticeInfo noticeInfo : pageResult.getPageResults ()){
			PageableNoticeInfo pageableNoticeInfo = new PageableNoticeInfo ();
			BeanCopier.copy (noticeInfo, pageableNoticeInfo);
			if(readList.contains (pageableNoticeInfo.getId ().toString ())){
				pageableNoticeInfo.setReaded (true);
			}
			pageInfo.getPageResults ().add (pageableNoticeInfo);
		}
		
		return pageInfo;
	}
	
	@Override
	public NoticeInfo readNotice (String receiver, Long id) {
		NoticeInfo notice = noticeInfoService.get (id);
		if (notice != null) {
			if (NoticeComponentConstants.BROADCAST_RECEIVER.equals (notice.getReceiver ())) {
				noticeReadService.readBroadcast (receiver, id);
			} else {
				notice.setReaded (true);
				noticeInfoService.update (notice);
			}
		}
		
		return notice;
	}
}
