/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.test.fqa;

import com.acooly.openapi.framework.core.test.AbstractApiServieTests;
import com.acooly.portlets.fqa.openapi.message.FqaApplyApiRequest;
import com.acooly.portlets.fqa.openapi.message.FqaApplyApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class FqaOpenApiTest extends AbstractApiServieTests {

    {
        this.gatewayUrl = "http://127.0.0.1:8084/gateway.do";
    }

    @Test
    public void testFqaApply() {
        FqaApplyApiRequest request = new FqaApplyApiRequest();
        request.setAsk("什么是卖家ID");
        request.setAskTypeCode("0001");
        request.setQuestion("卖家ID指的是收款方ID，可以是个人或企业会员ID。");
        request.setAuthor("zhangpu");
        FqaApplyApiResponse response = request(request, FqaApplyApiResponse.class);
        log.info("Fqa Apply result: {}", response);
    }

}
