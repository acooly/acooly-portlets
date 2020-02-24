/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-26 03:39
 */
package com.acooly.portlets.alog.client.api.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.portlets.alog.client.enums.ActionChannel;
import com.acooly.portlets.alog.client.enums.ActionOS;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 日志收集请求报文
 *
 * @author zhangpu
 * @date 2019-03-26 03:39
 */
@Getter
@Setter
public class AlogRequest extends ApiRequest {

    /**
     * 操作
     */
    @NotBlank
    @Size(max = 255)
    @OpenApiField(desc = "行为Key", constraint = "业务定义的用户行为埋点，可在后台定义", demo = "case_detail_view", ordinal = 1)
    private String actionKey;


    @Size(max = 64)
    @OpenApiField(desc = "行为分组", constraint = "可选，也可以在后台定义映射配置", demo = "app_case_view_group", ordinal = 2)
    private String actionGroup;

    /**
     * 操作名称
     */
    @Size(max = 32)
    @OpenApiField(desc = "行为名称", constraint = "可选，也可以在后台定义映射配置", demo = "案例详情", ordinal = 3)
    private String actionName;

    /**
     * 用户
     */
    @Size(max = 64)
    @OpenApiField(desc = "用户名", constraint = "已登录的用户标志，一般为用户名", demo = "zhangpu", ordinal = 4)
    private String userKey;

    /**
     * 平台
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @OpenApiField(desc = "平台", constraint = "一般指操作系统或设备类型", demo = "MAC", ordinal = 5)
    private ActionOS os;

    /**
     * 渠道
     */
    @NotNull
    @Enumerated(EnumType.STRING)
    @OpenApiField(desc = "渠道", constraint = "一般指浏览器或客户端端软件", demo = "App", ordinal = 6)
    private ActionChannel channel;

    /**
     * 渠道信息
     */
    @Size(max = 255)
    @OpenApiField(desc = "渠道信息", constraint = "渠道详情", demo = "Mac OS X-Chrome-Browser", ordinal = 7)
    private String channelInfo;

    /**
     * 渠道版本
     */
    @Size(max = 16)
    @OpenApiField(desc = "渠道版本", constraint = "渠道版本", demo = "72.0.3626.109", ordinal = 8)
    private String channelVersion;

    /**
     * 数据
     */
    @Size(max = 512)
    @OpenApiField(desc = "数据", constraint = "行为相关数据", demo = "{id:123}", ordinal = 9)
    private String data;

    /**
     * 访问IP
     */
    @Size(max = 16)
    @OpenApiField(desc = "客户端IP", constraint = "请求用户设备IP", demo = "218.189.02.11", ordinal = 10)
    private String userIp;


}
