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
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 反馈信息DTO
 *
 * @author zhangpu 2019-01-14 21:39
 */
@Data
public class FeedbackInfo extends FeedbackApplyInfo {

    @NotNull
    @OpenApiField(desc = "ID", constraint = "反馈ID", demo = "1", ordinal = 1)
    private Long id;

    /**
     * 回复内容
     */
    @Size(max = 512)
    @OpenApiField(desc = "回复内容", constraint = "回复内容", demo = "这里是后台运营处理后回复的内容", ordinal = 2)
    private String replyContent;

    /**
     * 回复人
     */
    @Size(max = 32)
    @OpenApiField(desc = "回复人", constraint = "回复人", demo = "admin", ordinal = 3)
    private String replyUser;

    /**
     * 状态
     */
    @OpenApiField(desc = "反馈状态", constraint = "反馈状态", demo = "processing", ordinal = 4)
    private FeedbackStatusEnum status;


}
