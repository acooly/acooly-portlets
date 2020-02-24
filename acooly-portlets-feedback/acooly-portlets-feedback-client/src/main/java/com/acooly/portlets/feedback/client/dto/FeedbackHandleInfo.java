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
import lombok.Data;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 反馈处理信息DTO
 *
 * @author zhangpu 2019-01-14 21:39
 */
@Data
public class FeedbackHandleInfo extends DtoBase {

    @NotNull
    @OpenApiField(desc = "ID", constraint = "反馈ID", demo = "1")
    private Long id;

    /**
     * 回复内容
     */
    @NotBlank
    @Size(max = 512)
    @OpenApiField(desc = "回复内容", constraint = "回复内容", demo = "这里是后台运营处理后回复的内容")
    private String replyContent;

    /**
     * 回复人
     */
    @Size(max = 32)
    @OpenApiField(desc = "回复人", constraint = "回复人", demo = "admin")
    private String replyUser;

    /**
     * 状态
     */
    @NotNull
    @OpenApiField(desc = "反馈状态", constraint = "反馈状态", demo = "processing")
    private FeedbackStatusEnum status;

    @Size(max = 126)
    @OpenApiField(desc = "备注", constraint = "备注", demo = "这里是运营备注信息")
    private String comments;

    public FeedbackHandleInfo() {
        setBizOrderNo(Ids.getDid());
    }
}
