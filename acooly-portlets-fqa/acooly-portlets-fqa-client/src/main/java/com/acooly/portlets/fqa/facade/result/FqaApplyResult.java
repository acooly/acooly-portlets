/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-02-28 11:56
 */
package com.acooly.portlets.fqa.facade.result;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.portlets.fqa.dto.FqaInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangpu
 * @date 2020-02-28 11:56
 */
@Getter
@Setter
public class FqaApplyResult extends ResultBase {

    /**
     * FQA详情
     */
    private FqaInfo fqaInfo;


    public FqaApplyResult() {
    }

    public FqaApplyResult(FqaInfo fqaInfo) {
        this.fqaInfo = fqaInfo;
    }
}
