/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.test.notice;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.portlets.notice.core.NoticeQueryService;
import com.acooly.portlets.notice.core.entity.NoticeInfo;
import com.acooly.portlets.notice.facade.dto.PageableNoticeInfo;
import com.acooly.test.NoWebTestBase;
import com.alibaba.fastjson.JSON;
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
    public void testReadNotice() {


    }

    @Test
    public void testNoticePageQuery() {
        Map<String, Object> params = new HashMap<>();
        params.put("EQ_readed", false);
        PageInfo pager = new PageInfo();

        PageInfo<PageableNoticeInfo> pageInfo = noticeQueryService.pageQuery("zhangpu1", pager, params, null);

        log.info("分页查询结果:{}", JSON.toJSONString(pageInfo, true));

    }

    @Test
    public void testUnReadCount() {
        long unreadCount = noticeQueryService.countUnreadNotice("zhangpu1", null);
        log.info("unreadCount:{}", unreadCount);
    }

    @Test
    public void testReadBroadcast() {
        NoticeInfo noticeInfo = noticeQueryService.readNotice("111111", 1L);
        Assert.assertTrue(noticeInfo.getId().equals(1) && noticeInfo.getReceiver().equals("ALL"));
    }

    @Test
    public void testReadAll() {
        noticeQueryService.readAll("zhangpu1", null);
    }
}
