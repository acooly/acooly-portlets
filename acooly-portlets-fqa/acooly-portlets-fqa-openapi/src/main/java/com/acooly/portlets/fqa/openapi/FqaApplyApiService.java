/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-02-28 11:44
 */
package com.acooly.portlets.fqa.openapi;

import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.openapi.framework.common.annotation.ApiDocNote;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ApiBusiType;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.portlets.common.PortletsContants;
import com.acooly.portlets.fqa.core.service.FqaService;
import com.acooly.portlets.fqa.dto.FqaApplyInfo;
import com.acooly.portlets.fqa.dto.FqaInfo;
import com.acooly.portlets.fqa.facade.api.FqaRemoteService;
import com.acooly.portlets.fqa.facade.order.FqaApplyOrder;
import com.acooly.portlets.fqa.facade.result.FqaApplyResult;
import com.acooly.portlets.fqa.openapi.message.FqaApplyApiRequest;
import com.acooly.portlets.fqa.openapi.message.FqaApplyApiResponse;
import com.alibaba.dubbo.config.annotation.Reference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * @author zhangpu
 * @date 2020-02-28 11:44
 */
@Slf4j
@ApiDocType(code = PortletsContants.APIDOC_TYPE_CODE, name = PortletsContants.APIDOC_TYPE_NAME)
@ApiDocNote("FQA提交接口。注意：FQA的分类为可选，如果没有填写，则默认为:default,否则请采用后台FQA专用分类管理模块定义后，线下方式提供给前端开发。")
@OpenApiService(name = "fqaApply", desc = "FQA发布", responseType = ResponseType.SYN, owner = "zhangpu", busiType = ApiBusiType.Trade)
public class FqaApplyApiService extends BaseApiService<FqaApplyApiRequest, FqaApplyApiResponse> {

    @Autowired(required = false)
    private FqaService fqaService;

    @Reference(version = "1.0")
    private FqaRemoteService fqaRemoteService;

    @Override
    protected void doService(FqaApplyApiRequest request, FqaApplyApiResponse response) {
        FqaInfo fqaInfo = null;
        FqaApplyInfo fqaApplyInfo = BeanCopier.copy(request, FqaApplyInfo.class);
        if (fqaService != null) {
            fqaInfo = fqaService.apply(fqaApplyInfo);
        } else {
            FqaApplyOrder order = new FqaApplyOrder(fqaApplyInfo);
            FqaApplyResult result = fqaRemoteService.fqaApply(order);
            fqaInfo = result.getFqaInfo();
        }
        response.setFqaInfo(fqaInfo);
    }
}