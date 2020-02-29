/*
 * www.acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2019-01-14 21:39 创建
 */
package com.acooly.portlets.feedback.client.dto;

import com.acooly.core.common.facade.DtoBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.portlets.feedback.client.enums.FeedbackStatusEnum;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 反馈处理信息DTO
 *
 * @author zhangpu 2019-01-14 21:39
 */
@Getter
@Setter
@ApiModel(value = "FeedbackHandleInfo", description = "客户反馈处理信息")
public class FeedbackHandleInfo extends DtoBase {

    @NotNull
    @OpenApiField(desc = "ID", constraint = "反馈ID", demo = "1")
    @ApiModelProperty(value = "ID", example = "1", required = true, position = 1)
    private Long id;

    /**
     * 回复内容
     */
    @NotBlank
    @Size(max = 512)
    @OpenApiField(desc = "回复内容", constraint = "回复内容", demo = "这里是后台运营处理后回复的内容")
    @ApiModelProperty(value = "回复内容", example = "这里是后台运营处理后回复的内容", required = true, position = 2)
    private String replyContent;

    /**
     * 回复人
     */
    @Size(max = 32)
    @OpenApiField(desc = "回复人", constraint = "回复人", demo = "admin")
    @ApiModelProperty(value = "回复人", example = "admin", position = 3)
    private String replyUser;

    /**
     * 状态
     */
    @NotNull
    @OpenApiField(desc = "反馈状态", constraint = "反馈状态", demo = "processing")
    @ApiModelProperty(value = "反馈状态", example = "processing", required = true, position = 4)
    private FeedbackStatusEnum status;

    @Size(max = 126)
    @OpenApiField(desc = "备注", constraint = "备注", demo = "这里是运营备注信息")
    @ApiModelProperty(value = "备注", example = "这里是运营备注信息", required = false, position = 5)
    private String comments;

    public FeedbackHandleInfo() {
        setBizOrderNo(Ids.getDid());
    }
}
