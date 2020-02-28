/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-02-28 11:54
 */
package com.acooly.portlets.fqa.facade.order;

import com.acooly.core.common.facade.OrderBase;
import com.acooly.portlets.fqa.dto.FqaApplyInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangpu
 * @date 2020-02-28 11:54
 */
@Getter
@Setter
public class FqaApplyOrder extends OrderBase {

    /**
     * FQA提交信息
     */
    private FqaApplyInfo fqaApplyInfo;

    public FqaApplyOrder() {
    }

    public FqaApplyOrder(FqaApplyInfo fqaApplyInfo) {
        this.fqaApplyInfo = fqaApplyInfo;
    }
}
