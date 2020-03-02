/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-02-29 04:04
 */
package com.acooly.portlets.fqa.openapi;

import com.acooly.core.common.facade.SingleOrder;
import com.acooly.core.common.facade.SingleResult;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocNote;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ApiBusiType;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.portlets.common.PortletsContants;
import com.acooly.portlets.fqa.core.entity.Fqa;
import com.acooly.portlets.fqa.core.service.FqaService;
import com.acooly.portlets.fqa.dto.FqaApplyInfo;
import com.acooly.portlets.fqa.dto.FqaInfo;
import com.acooly.portlets.fqa.facade.api.FqaRemoteService;
import com.acooly.portlets.fqa.facade.order.FqaApplyOrder;
import com.acooly.portlets.fqa.openapi.message.FqaInfoApiRequest;
import com.acooly.portlets.fqa.openapi.message.FqaInfoApiResponse;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FQA详情接口
 *
 * @author zhangpu
 * @date 2020-02-29 04:04
 */
@Slf4j
@ApiDocType(code = PortletsContants.APIDOC_TYPE_CODE, name = PortletsContants.APIDOC_TYPE_NAME)
@ApiDocNote("FQA详情，在列表查询后，传入FQA的id，查询FQA所有信息的情况下使用。该接口的访问会增加hit(PV)")
@OpenApiService(name = "fqaInfo", desc = "FQA详情", responseType = ResponseType.SYN, owner = "zhangpu", busiType = ApiBusiType.Query)
public class FqaInfoApiService extends BaseApiService<FqaInfoApiRequest, FqaInfoApiResponse> {

    @Autowired(required = false)
    private FqaService fqaService;

    @Reference(version = "1.0")
    private FqaRemoteService fqaRemoteService;


    @Override
    protected void doService(FqaInfoApiRequest request, FqaInfoApiResponse response) {
        FqaInfo fqaInfo = null;
        FqaApplyInfo fqaApplyInfo = BeanCopier.copy(request, FqaApplyInfo.class);
        if (fqaService != null) {
            fqaInfo = fqaService.detail(request.getId());
        } else {
            FqaApplyOrder order = new FqaApplyOrder(fqaApplyInfo);
            SingleResult<FqaInfo> result = fqaRemoteService.get(SingleOrder.from(request.getId()));
            fqaInfo = result.getDto();
        }
        response.setFqaInfo(fqaInfo);

    }
}