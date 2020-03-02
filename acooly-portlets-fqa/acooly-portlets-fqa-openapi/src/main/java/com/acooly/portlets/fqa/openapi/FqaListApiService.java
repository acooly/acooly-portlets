/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-02-29 04:09
 */
package com.acooly.portlets.fqa.openapi;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.facade.PageOrder;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.utils.Strings;
import com.acooly.openapi.framework.common.annotation.ApiDocNote;
import com.acooly.openapi.framework.common.annotation.ApiDocType;
import com.acooly.openapi.framework.common.annotation.OpenApiService;
import com.acooly.openapi.framework.common.enums.ApiBusiType;
import com.acooly.openapi.framework.common.enums.ResponseType;
import com.acooly.openapi.framework.core.service.base.BaseApiService;
import com.acooly.portlets.common.PortletsContants;
import com.acooly.portlets.fqa.core.service.FqaService;
import com.acooly.portlets.fqa.dto.FqaInfo;
import com.acooly.portlets.fqa.facade.api.FqaRemoteService;
import com.acooly.portlets.fqa.openapi.message.FqaListApiRequest;
import com.acooly.portlets.fqa.openapi.message.FqaListApiResponse;
import com.alibaba.dubbo.config.annotation.Reference;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * FQA分页查询
 *
 * @author zhangpu
 * @date 2020-02-29 04:09
 */
@Slf4j
@ApiDocType(code = PortletsContants.APIDOC_TYPE_CODE, name = PortletsContants.APIDOC_TYPE_NAME)
@ApiDocNote("FQA分页查询接口，提供多条件查询。" +
        "<li>includeQuestion: 控制结果列表中的内容是否包含question的内容。</li>" +
        "<li>查询条件：分类查询</li>" +
        "<li>排序条件：固定为 hit desc, createTime desc</li>")
@OpenApiService(name = "fqaList", desc = "FQA分页查询", responseType = ResponseType.SYN, owner = "zhangpu", busiType = ApiBusiType.Query)
public class FqaListApiService extends BaseApiService<FqaListApiRequest, FqaListApiResponse> {

    @Autowired(required = false)
    private FqaService fqaService;

    @Reference(version = "1.0")
    private FqaRemoteService fqaRemoteService;

    @Override
    protected void doService(FqaListApiRequest request, FqaListApiResponse response) {
        Map<String, Object> map = Maps.newHashMap();
        if (Strings.isNotBlank(request.getAskTypeCode())) {
            map.put("EQ_askTypeCode", request.getAskTypeCode());
        }
        if (Strings.isNotBlank(request.getKeywords())) {
            map.put("LIKE_ask", request.getKeywords());
        }
        Map<String, Boolean> sortMap = Maps.newHashMap();
        sortMap.put("hits", false);
        sortMap.put("id", false);
        if (fqaService != null) {
            PageInfo pageInfo = new PageInfo(request.getLimit(), request.getStart());
            fqaService.list(pageInfo, map, sortMap);
            response.setRows(pageInfo.getPageResults());
            response.setTotalPages(pageInfo.getTotalPage());
            response.setTotalRows(pageInfo.getTotalCount());
        } else {
            PageOrder order = request.toOrder(PageOrder.class);
            PageResult<FqaInfo> result = fqaRemoteService.fqaList(order);
            response.setPageResult(result);
        }
    }
}