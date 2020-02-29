/*
 * www.acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2019-01-14 21:39 创建
 */
package com.acooly.portlets.feedback.client.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.portlets.feedback.client.enums.FeedbackStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 反馈信息DTO
 *
 * @author zhangpu 2019-01-14 21:39
 */
@Getter
@Setter
@ApiModel(value = "FeedbackInfo", description = "客户反馈详情")
public class FeedbackInfo extends FeedbackApplyInfo {

    @NotNull
    @OpenApiField(desc = "ID", constraint = "反馈ID", demo = "1", ordinal = 1)
    @ApiModelProperty(value = "ID", example = "1", notes = "物理ID", position = 1)
    private Long id;

    @Size(max = 128)
    @OpenApiField(desc = "业务分类名称", constraint = "业务分类名称", demo = "智能分账", ordinal = 13)
    @ApiModelProperty(value = "业务分类名称", example = "智能分账", position = 13)
    private String busiName;

    /**
     * 回复内容
     */
    @Size(max = 512)
    @OpenApiField(desc = "回复内容", constraint = "回复内容", demo = "这里是后台运营处理后回复的内容", ordinal = 14)
    @ApiModelProperty(value = "回复内容", example = "这里是后台运营处理后回复的内容", position = 14)
    private String replyContent;

    /**
     * 回复人
     */
    @Size(max = 32)
    @OpenApiField(desc = "回复人", constraint = "回复人", demo = "admin", ordinal = 15)
    @ApiModelProperty(value = "回复人", example = "admin", position = 15)
    private String replyUser;

    /**
     * 状态
     */
    @OpenApiField(desc = "反馈状态", constraint = "反馈状态", demo = "processing", ordinal = 16)
    @ApiModelProperty(value = "反馈状态", example = "processing", position = 16)
    private FeedbackStatusEnum status;


    /**
     * 备注
     */
    @Size(max = 45)
    @OpenApiField(desc = "备注", constraint = "备注", demo = "备注补充说明", ordinal = 17)
    @ApiModelProperty(value = "备注", example = "备注补充说明", position = 17)
    private String comments;

    @NotNull
    @OpenApiField(desc = "创建时间", constraint = "创建时间", demo = "2020-02-28 12:23:10", ordinal = 18)
    @ApiModelProperty(value = "创建时间", example = "2020-02-28 12:23:10", required = true, position = 18)
    private Date createTime;

    @NotNull
    @OpenApiField(desc = "更新时间", constraint = "更新时间", demo = "2020-02-28 12:23:10", ordinal = 19)
    @ApiModelProperty(value = "更新时间", example = "2020-02-28 12:23:10", required = true, position = 19)
    private Date updateTime;


}
