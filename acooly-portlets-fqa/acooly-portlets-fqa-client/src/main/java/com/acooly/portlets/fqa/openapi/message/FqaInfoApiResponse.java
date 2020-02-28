/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-02-28 11:45
 */
package com.acooly.portlets.fqa.openapi.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.portlets.fqa.dto.FqaInfo;
import lombok.Getter;
import lombok.Setter;

/**
 * @author zhangpu
 * @date 2020-02-28 11:45
 */
@Getter
@Setter
public class FqaInfoApiResponse extends ApiResponse {

    @OpenApiField(desc = "FQA详情", constraint = "FQA详情", ordinal = 1)
    private FqaInfo fqaInfo;

}
