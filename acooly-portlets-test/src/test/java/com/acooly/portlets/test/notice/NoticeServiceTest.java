/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.test.notice;

import com.acooly.core.utils.Ids;
import com.acooly.portlets.notice.core.NoticeService;
import com.acooly.portlets.notice.core.dto.NoticeMessage;
import com.acooly.portlets.notice.core.service.NoticeInfoService;
import com.acooly.portlets.notice.facade.enums.DeviceTypeEnum;
import com.acooly.portlets.test.notice.CustomNoticeGroupEnum;
import com.acooly.test.NoWebTestBase;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liubin@prosysoft.com on 2017/12/13.
 */
public class NoticeServiceTest extends NoWebTestBase {


    @Autowired
    private NoticeService noticeService;

    @Autowired
    private NoticeInfoService infoService;


    @Test
    public void testBroadcast() {
        NoticeMessage noticeMessage = new NoticeMessage();
        noticeMessage.setPushNo(Ids.getDid());
        noticeMessage.setPush(false);
        noticeMessage.setTitle("测试广播");
        noticeMessage.setContent("这是广播、广播、广播、广播、广播、广播、广播、广播、广播、广播、广播、广播、广播、广播、广播、广播、广播、广播、广播、广播。");
        noticeMessage.setPush(false);
        noticeMessage.setCustomGroup(CustomNoticeGroupEnum.BUSINESS.code());

        Map<String, Object> context = new HashMap<>();
        context.put("customerId", "12312312312");
        context.put("userName", "刘斌");
        context.put("realName", "刘斌");

        noticeMessage.setContext(context);

        noticeService.broadcast(noticeMessage);
    }

    @Test
    public void testGroup() {
        NoticeMessage noticeMessage = new NoticeMessage();
        noticeMessage.setPushNo(Ids.getDid());
        noticeMessage.setTitle("测试群发消息");
        noticeMessage.setContent("这是消息、消息、消息。");
        noticeMessage.setPush(false);
        noticeMessage.setCustomGroup(CustomNoticeGroupEnum.BUSINESS_BUY.code());

        Map<String, Object> context = new HashMap<>();
        context.put("customerId", "12312312312");
        context.put("userName", "刘斌");
        context.put("realName", "刘斌");

        noticeMessage.setContext(context);
        noticeMessage.setDeviceType(DeviceTypeEnum.ANDROID);

        noticeService.group(noticeMessage, Lists.newArrayList("zhangpu1", "zhangpu2", "zhangpu3", "zhangpu4"));

    }


}
