/*
 * www.acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2019-01-14 21:30 创建
 */
package com.acooly.portlets.feedback.client.dto;

import com.acooly.core.common.facade.DtoBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.portlets.feedback.client.enums.FeedbackTypeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 提交反馈信息DTO
 *
 * @author zhangpu 2019-01-14 21:30
 */
@Getter
@Setter
public class FeedbackApplyInfo extends DtoBase {

    /**
     * 类型
     */
    @NotNull
    @OpenApiField(desc = "类型", constraint = "反馈类型", demo = "suggest", ordinal = 1)
    private FeedbackTypeEnum type;

    /**
     * 内容
     */
    @NotBlank
    @Size(max = 512)
    @OpenApiField(desc = "反馈内容", constraint = "反馈内容,不建议HTML格式，推荐纯文本", demo = "这是我提交的建议内容", ordinal = 2)
    private String content;

    /**
     * 标题
     */
    @Size(max = 128)
    @OpenApiField(desc = "反馈标题", constraint = "反馈标题", demo = "这是我提交的建议的标题", ordinal = 3)
    private String title;

    /**
     * 用户标志
     */
    @Size(max = 64)
    @OpenApiField(desc = "用户标志", constraint = "用户标志，可以是用户ID或用户编码等用户的唯一标志", demo = "1", ordinal = 4)
    private String userKey;

    /**
     * 用户名
     */
    @Size(max = 32)
    @OpenApiField(desc = "用户名", constraint = "用户名，冗余信息便于可读", demo = "zhangpu", ordinal = 5)
    private String userName;

    /**
     * 用户IP
     */
    @Size(max = 32)
    @OpenApiField(desc = "用户IP", constraint = "请求的IP", demo = "219.210.98.21", ordinal = 6)
    private String userIp;

    /**
     * 联系电话
     */
    @Size(max = 21)
    @OpenApiField(desc = "联系电话", constraint = "联系电话", demo = "13896177630", ordinal = 7)
    private String phoneNo;

    /**
     * 联系邮箱
     */
    @Size(max = 255)
    @OpenApiField(desc = "联系邮箱", constraint = "联系邮箱", demo = "zhangpu@acooly.cn", ordinal = 8)
    private String mail;

    /**
     * 联系地址
     */
    @Size(max = 128)
    @OpenApiField(desc = "联系地址", constraint = "联系地址", demo = "石油路123号7-7-7", ordinal = 9)
    private String address;

    /**
     * 联系信息
     */
    @Size(max = 255)
    @OpenApiField(desc = "其他联系信息", constraint = "补充的联系信息", demo = "公司电话:76878903", ordinal = 10)
    private String contactInfo;

    public FeedbackApplyInfo() {
        setBizOrderNo(Ids.getDid());
    }

    /**
     * 必填参数
     *
     * @param type
     * @param content
     */
    public FeedbackApplyInfo(FeedbackTypeEnum type, String content) {
        this();
        this.type = type;
        this.content = content;
    }
}
