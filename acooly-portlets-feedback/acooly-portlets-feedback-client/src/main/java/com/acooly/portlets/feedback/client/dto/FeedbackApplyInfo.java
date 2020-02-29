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
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "FeedbackApplyInfo", description = "客户反馈申请对象")
public class FeedbackApplyInfo extends DtoBase {

    /**
     * 类型
     */
    @NotNull
    @OpenApiField(desc = "类型", constraint = "反馈类型", demo = "suggest", ordinal = 2)
    @ApiModelProperty(value = "类型", example = "suggest", notes = "默认为：suggest(建议)", position = 2)
    private FeedbackTypeEnum type = FeedbackTypeEnum.suggest;

    /**
     * 内容
     */
    @NotBlank
    @Size(max = 512)
    @OpenApiField(desc = "反馈内容", constraint = "反馈内容,不建议HTML格式，推荐纯文本", demo = "这是我提交的建议内容", ordinal = 3)
    @ApiModelProperty(value = "反馈内容", notes = "反馈内容,不建议HTML格式，推荐纯文本", example = "这是我提交的建议内容", required = true, position = 3)
    private String content;

    /**
     * 标题
     */
    @Size(max = 128)
    @OpenApiField(desc = "反馈标题", constraint = "反馈标题", demo = "这是我提交的建议的标题", ordinal = 4)
    @ApiModelProperty(value = "反馈标题", example = "这是我提交的建议的标题", position = 4)
    private String title;

    /**
     * 用户标志
     */
    @Size(max = 64)
    @OpenApiField(desc = "用户标志", constraint = "用户标志，可以是用户ID或用户编码等用户的唯一标志", demo = "1", ordinal = 5)
    @ApiModelProperty(value = "反馈标题", example = "这是我提交的建议的标题", position = 5)
    private String userKey;

    /**
     * 用户名
     */
    @Size(max = 32)
    @OpenApiField(desc = "用户名", constraint = "用户名，冗余信息便于可读", demo = "zhangpu", ordinal = 6)
    @ApiModelProperty(value = "用户名", example = "zhangpu", position = 6)
    private String userName;

    /**
     * 用户IP
     */
    @Size(max = 32)
    @OpenApiField(desc = "用户IP", constraint = "请求的IP", demo = "219.210.98.21", ordinal = 7)
    @ApiModelProperty(value = "请求的IP", example = "219.210.98.21", position = 7)
    private String userIp;

    /**
     * 联系电话
     */
    @Size(max = 21)
    @OpenApiField(desc = "联系电话", constraint = "联系电话", demo = "13896177630", ordinal = 8)
    @ApiModelProperty(value = "联系电话", example = "13896177630", position = 8)
    private String phoneNo;

    /**
     * 联系邮箱
     */
    @Size(max = 255)
    @OpenApiField(desc = "联系邮箱", constraint = "联系邮箱", demo = "zhangpu@acooly.cn", ordinal = 9)
    @ApiModelProperty(value = "联系邮箱", example = "zhangpu@acooly.cn", position = 9)
    private String mail;

    /**
     * 联系地址
     */
    @Size(max = 128)
    @OpenApiField(desc = "联系地址", constraint = "联系地址", demo = "石油路123号7-7-7", ordinal = 10)
    @ApiModelProperty(value = "联系地址", example = "石油路123号7-7-7", position = 10)
    private String address;

    /**
     * 联系信息
     */
    @Size(max = 255)
    @OpenApiField(desc = "其他联系信息", constraint = "补充的联系信息", demo = "公司电话:76878903", ordinal = 11)
    @ApiModelProperty(value = "其他联系信息", example = "公司电话:76878903", position = 11)
    private String contactInfo;


    @Size(max = 128)
    @OpenApiField(desc = "业务分类编码", constraint = "业务分类编码，在管理后台定义。", demo = "feedback_0001", ordinal = 12)
    @ApiModelProperty(value = "业务分类编码", notes = "业务分类在后台定义，请通过接口:treeTypeList接口获取", example = "0001", position = 12)
    private String busiCode;

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
