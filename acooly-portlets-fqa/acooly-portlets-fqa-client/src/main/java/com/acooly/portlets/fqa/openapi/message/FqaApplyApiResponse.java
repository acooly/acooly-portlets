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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author zhangpu
 * @date 2020-02-28 11:45
 */
@Getter
@Setter
@ApiModel(value = "FqaApplyApiResponse", description = "FQA提交响应报文")
public class FqaApplyApiResponse extends ApiResponse {

    @NotNull
    @OpenApiField(desc = "FQA详情", constraint = "FQA详情", ordinal = 1)
    @ApiModelProperty(value = "FQA详情", required = true)
    private FqaInfo fqaInfo;

}
