/*
 * acooly.cn Inc.
 * Copyright (c) 2020 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2020-02-25
 */
package com.acooly.portlets.fqa.dto;


import com.acooly.core.utils.enums.AbleStatus;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * p_fqa 详情DTO
 *
 * @author zhangpu@acooly.cn
 * Date: 2020-02-28
 */
@Getter
@Setter
@ApiModel(value = "FqaInfo", description = "FQA详情")
public class FqaInfo extends FqaApplyInfo {

    @NotNull
    @OpenApiField(desc = "ID", constraint = "物理ID", demo = "1", ordinal = 1)
    @ApiModelProperty(value = "ID", example = "1", required = true, position = 1)
    private Long id;

    /**
     * 问题分类
     */
    @Size(max = 32)
    @OpenApiField(desc = "分类名称", constraint = "问题分类名称", demo = "技术接入", ordinal = 6)
    @ApiModelProperty(value = "分类名称", example = "技术接入", position = 6)
    private String askTypeName;

    /**
     * 访问量
     */
    @Max(999999999)
    @OpenApiField(desc = "访问量", constraint = "访问量", demo = "", ordinal = 7)
    @ApiModelProperty(value = "访问量", notes = "PV", example = "100", position = 7)
    private Integer hits;

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

    @NotNull
    @OpenApiField(desc = "创建时间", constraint = "创建时间", demo = "2020-02-28 12:23:10", ordinal = 10)
    @ApiModelProperty(value = "创建时间", example = "2020-02-28 12:23:10", required = true, position = 10)
    private Date createTime;

    @NotNull
    @OpenApiField(desc = "更新时间", constraint = "更新时间", demo = "2020-02-28 12:23:10", ordinal = 11)
    @ApiModelProperty(value = "更新时间", example = "2020-02-28 12:23:10", required = true, position = 11)
    private Date updateTime;

}
