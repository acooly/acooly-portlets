/*
 * acooly.cn Inc.
 * Copyright (c) 2020 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2020-02-25
 */
package com.acooly.portlets.fqa.dto;


import com.acooly.core.common.facade.InfoBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * p_fqa 申请（新增/创建/编辑）DTO
 *
 * @author zhangpu@acooly.cn
 * Date: 2020-02-28
 */
@Getter
@Setter
@ApiModel(value = "FqaApplyInfo", description = "FQA提交对象")
public class FqaApplyInfo extends InfoBase {
    /**
     * 问题
     */
    @NotBlank
    @Size(max = 128)
    @OpenApiField(desc = "问题", constraint = "问题", demo = "什么是卖家ID", ordinal = 2)
    @ApiModelProperty(value = "问题", example = "什么是卖家ID", required = true, position = 2)
    private String ask;

    /**
     * 答案
     */
    @Size(max = 1024)
    @OpenApiField(desc = "答案", constraint = "答案", demo = "卖家ID指的是收款方ID，可以是个人或企业会员ID。", ordinal = 3)
    @ApiModelProperty(value = "答案", example = "卖家ID指的是收款方ID，可以是个人或企业会员ID。", required = false, position = 3)
    private String question;

    /**
     * 作者
     */
    @Size(max = 16)
    @OpenApiField(desc = "作者", constraint = "作者", demo = "zhangpu", ordinal = 4)
    @ApiModelProperty(value = "作者", example = "zhangpu", required = false, position = 4)
    private String author;

    /**
     * 问题分类
     */
    @Size(max = 32)
    @OpenApiField(desc = "问题分类编码", constraint = "问题分类，根据独立多级分类模块自定义", demo = "0001", ordinal = 5)
    @ApiModelProperty(value = "问题分类编码", notes = "问题分类，根据独立多级分类模块自定义,来自后台管理定义，请参考接口: treeTypeList",
            example = "0001", required = false, position = 5)
    private String askTypeCode;

}
