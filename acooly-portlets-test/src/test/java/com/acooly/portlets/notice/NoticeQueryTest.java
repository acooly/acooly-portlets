/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.portlets.notice.core.NoticeQueryService;
import com.acooly.portlets.notice.core.dto.PageableNoticeInfo;
import com.acooly.portlets.notice.core.entity.NoticeInfo;
import com.acooly.test.NoWebTestBase;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liubin@prosysoft.com on 2017/12/13.
 */
@Slf4j
public class NoticeQueryTest extends NoWebTestBase {
	
	@Autowired
	private NoticeQueryService noticeQueryService;
	
	
	@Test
	public void testReadNotice(){
	
	
		
	}
	
	@Test
	public void testNoticePageQuery(){
		Map<String,Object> params = new HashMap<> ();
		
		PageInfo pager = new PageInfo ();
		
		PageInfo<PageableNoticeInfo> pageInfo = noticeQueryService.pageQuery ("yanjun89", pager, params, null);
		
		log.info ("分页查询结果:{}",pageInfo);
		
	}
	
	@Test
	public void testReadBroadcast(){
		NoticeInfo noticeInfo = noticeQueryService.readNotice ("111111", 1L);
		Assert.assertTrue (noticeInfo.getId ().equals (1) && noticeInfo.getReceiver ().equals ("ALL"));
	}
}
