/*
 * acooly.cn Inc.
 * Copyright (c) 2020 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2020-02-25
 */
package com.acooly.portlets.fqa.openapi.message;


import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Size;

/**
 * FQA ApiMessage
 * 注意：本类自定生成，不能直接使用，请开发人员根据业务选择下面的属性用于快速定义OpenApi的报文，
 * 主要减轻定义@OpenApiField和JSR303的工作。
 *
 * @author zhangpu@acooly.cn
 * Date: 2020-02-25 05:00:02
 */
@Getter
@Setter
public class FqaListApiRequest extends PageApiRequest {

    /**
     * 问题分类
     */
    @Size(max = 32)
    @OpenApiField(desc = "问题分类编码", constraint = "问题分类，根据独立多级分类模块自定义", demo = "0001", ordinal = 2)
    @ApiModelProperty(value = "问题分类编码", notes = "问题分类，根据独立多级分类模块自定义,来自后台管理定义，请参考接口: treeTypeList",
            example = "0001", required = false, position = 2)
    private String askTypeCode;

    @Size(max = 32)
    @OpenApiField(desc = "搜索关键字", constraint = "搜索关键字", demo = "签名", ordinal = 1)
    @ApiModelProperty(value = "搜索关键字", example = "签名", required = false, position = 1)
    private String keywords;


}
