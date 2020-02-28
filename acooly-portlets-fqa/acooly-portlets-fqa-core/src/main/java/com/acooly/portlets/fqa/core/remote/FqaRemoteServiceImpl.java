/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-02-28 12:17
 */
package com.acooly.portlets.fqa.core.remote;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.facade.PageOrder;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.SingleOrder;
import com.acooly.core.common.facade.SingleResult;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.module.appservice.AppService;
import com.acooly.portlets.fqa.core.service.FqaService;
import com.acooly.portlets.fqa.dto.FqaInfo;
import com.acooly.portlets.fqa.facade.api.FqaRemoteService;
import com.acooly.portlets.fqa.facade.order.FqaApplyOrder;
import com.acooly.portlets.fqa.facade.result.FqaApplyResult;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author zhangpu
 * @date 2020-02-28 12:17
 */
@Slf4j
public class FqaRemoteServiceImpl implements FqaRemoteService {

    @Autowired
    private FqaService fqaService;

    @AppService
    @Override
    public FqaApplyResult fqaApply(FqaApplyOrder fqaApplyOrder) {
        return new FqaApplyResult(fqaService.apply(fqaApplyOrder.getFqaApplyInfo()));
    }

    @Override
    public PageResult<FqaInfo> fqaList(PageOrder pageOrder) {
        PageInfo pageInfo = fqaService.query(pageOrder.getPageInfo(),
                pageOrder.getMap(), pageOrder.getSortMap());
        List<FqaInfo> faqInfos = Lists.transform(pageInfo.getPageResults(),
                f -> BeanCopier.copy(f, FqaInfo.class));
        pageInfo.setPageResults(faqInfos);
        return PageResult.from(pageInfo);
    }

    @Override
    public SingleResult<FqaInfo> get(SingleOrder<Long> id) {
        return SingleResult.from(fqaService.detail(id.getDto()));
    }
}
