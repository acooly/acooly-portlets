/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-26 17:19
 */
package com.acooly.portlets.test.alog;

import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.openapi.framework.core.test.AbstractApiServieTests;
import com.acooly.portlets.alog.client.api.message.AlogRequest;
import com.acooly.portlets.alog.client.enums.ActionChannel;
import com.acooly.portlets.alog.client.enums.ActionOS;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author zhangpu
 * @date 2019-03-26 17:19
 */
@Slf4j
public class AlogApiServiceTest extends AbstractApiServieTests {
    {
        gatewayUrl = "http://127.0.0.1:8080/gateway.do";
    }

    @Test
    public void testAlog() {
        AlogRequest alogRequest = new AlogRequest();
        alogRequest.setActionKey("case_detail_view");
        alogRequest.setChannel(ActionChannel.MicroMessenger);
        alogRequest.setChannelInfo("java单元测试");
        alogRequest.setChannelVersion("1.0.1");
        alogRequest.setOs(ActionOS.ANDROID);
        alogRequest.setUserKey("zhangpu");
        alogRequest.setData("这是附加数据");
        request(alogRequest, ApiResponse.class);
    }
}
