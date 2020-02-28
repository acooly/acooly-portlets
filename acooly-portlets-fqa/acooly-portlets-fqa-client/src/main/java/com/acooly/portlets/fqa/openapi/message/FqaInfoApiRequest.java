/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-02-28 11:02
 */
package com.acooly.portlets.fqa.openapi.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * FQA详情
 *
 * @author zhangpu
 * @date 2020-02-28 11:02
 */
@Getter
@Setter
public class FqaInfoApiRequest extends ApiRequest {

    @NotNull
    @OpenApiField(desc = "ID", constraint = "问题ID", demo = "1", ordinal = 1)
    private Long id;

}
