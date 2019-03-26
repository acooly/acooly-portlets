/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-26 16:27
 */
package com.acooly.portlets.alog.openapi.api;

import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.ApiConstants;
import com.acooly.openapi.framework.common.annotation.ApiDocNote;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.context.ApiContextHolder;
import com.acooly.openapi.framework.common.enums.ApiBusiType;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.portlets.alog.client.api.message.AlogRequest;
import com.acooly.portlets.alog.core.dto.ActionLogInfo;
import com.acooly.portlets.alog.core.service.AlogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhangpu
 * @date 2019-03-26 16:27
 */
@Slf4j
@ApiDocType(code = ApiConstants.BUILDIN_APIDOC_CODE, name = ApiConstants.BUILDIN_APIDOC_NAME)
@ApiDocNote("提供Alog行为日志收集，App端根据埋点配置，在用户产生应为后，异步调用该接口上送用户行为日志")
@OpenApiService(name = "alog", desc = "行为日志", responseType = ResponseType.SYN, busiType = ApiBusiType.Trade, owner = "zhangpu")
public class AlogApiService extends BaseApiService<AlogRequest, ApiResponse> {

    @Autowired
    private AlogService alogService;

    @Override
    protected void doService(AlogRequest request, ApiResponse response) {
        ActionLogInfo actionLogInfo = BeanCopier.copy(request, ActionLogInfo.class);
        alogService.log(actionLogInfo, ApiContextHolder.getApiContext().getOrignalRequest());
    }
}
