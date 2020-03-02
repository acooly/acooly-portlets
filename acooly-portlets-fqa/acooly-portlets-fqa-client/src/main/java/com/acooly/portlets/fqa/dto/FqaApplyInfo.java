/*
 * acooly.cn Inc.
 * Copyright (c) 2020 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2020-02-25
 */
package com.acooly.portlets.fqa.dto;


import com.acooly.core.common.facade.InfoBase;
import com.acooly.core.utils.enums.AbleStatus;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiFieldCondition;
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

    @OpenApiFieldCondition("新增时为空，编辑时不为空")
    @OpenApiField(desc = "ID", constraint = "物理ID", demo = "1", ordinal = 1)
    @ApiModelProperty(value = "ID", example = "1", notes = "新增时为空，编辑时不为空", required = false, position = 1)
    private Long id;

    /**
     * 问题
     */
    @NotBlank
    @Size(max = 128)
    @OpenApiField(desc = "问题", constraint = "问题", demo = "什么是卖家ID", ordinal = 2)
    @ApiModelProperty(value = "问题", example = "什么是卖家ID", required = true, position = 2)
    private String question;

    /**
     * 答案
     */
    @OpenApiFieldCondition("列表分页查询时候为空; 添加和单笔详情查询时不为空")
    @OpenApiField(desc = "答案", constraint = "答案,支持富文本方式，建议简短在1024内。", demo = "卖家ID指的是收款方ID，可以是个人或企业会员ID。", ordinal = 3)
    @ApiModelProperty(value = "答案", notes = "支持富文本方式，建议简短在1024内,列表分页查询时候为空; 添加和单笔详情查询时不为空", example = "卖家ID指的是收款方ID，可以是个人或企业会员ID。", required = false, position = 3)
    private String answer;

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
    private String typeCode;

    /**
     * 状态
     */
    @OpenApiField(desc = "状态", constraint = "状态", ordinal = 8)
    @ApiModelProperty(value = "状态", example = "enable", required = true, position = 8)
    private AbleStatus status;

    /**
     * 备注
     */
    @Size(max = 45)
    @OpenApiField(desc = "备注", constraint = "备注", demo = "备注补充说明", ordinal = 9)
    @ApiModelProperty(value = "备注", example = "备注补充说明", position = 9)
    private String comments;


}
