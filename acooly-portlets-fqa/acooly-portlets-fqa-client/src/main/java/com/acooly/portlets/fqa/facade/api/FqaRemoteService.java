/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-02-28 12:14
 */
package com.acooly.portlets.fqa.facade.api;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.SingleOrder;
import com.acooly.core.common.facade.SingleResult;
import com.acooly.portlets.fqa.dto.FqaInfo;
import com.acooly.portlets.fqa.facade.order.FqaApplyOrder;
import com.acooly.portlets.fqa.facade.result.FqaApplyResult;

/**
 * FQA远程服务
 *
 * @author zhangpu
 * @date 2020-02-28 12:14
 */
public interface FqaRemoteService {

    /**
     * FQA提交
     *
     * @param fqaApplyOrder
     * @return
     */
    FqaApplyResult fqaApply(FqaApplyOrder fqaApplyOrder);


    /**
     * FQA 分页查询
     *
     * @param fqaListOrder
     * @return
     */
    PageResult<FqaInfo> fqaList(PageOrder pageOrder);

    /**
     * 根据ID查询详情
     *
     * @param id
     * @return
     */
    SingleResult<FqaInfo> get(SingleOrder<Long> id);

}
