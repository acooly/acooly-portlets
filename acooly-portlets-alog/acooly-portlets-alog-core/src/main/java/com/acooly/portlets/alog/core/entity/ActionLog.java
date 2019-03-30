/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-01-10
 */
package com.acooly.portlets.alog.core.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.portlets.alog.client.enums.ActionChannel;
import com.acooly.portlets.alog.client.enums.ActionOS;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 访问日志 Entity
 *
 * @author zhangpu@acooly.cn
 * Date: 2019-01-10 18:10:55
 */
@Entity
@Table(name = "p_action_log")
@Getter
@Setter
public class ActionLog extends AbstractEntity {

    /**
     * 操作
     */
    @NotEmpty
    @Size(max = 512)
    private String actionKey;

    /**
     * 操作名称
     */
    @Size(max = 32)
    private String actionName;

    @Size(max = 64)
    private String actionGroup;

    /**
     * URL连接
     */
    @Size(max = 512)
    private String actionUrl;

    /**
     * 用户名
     */
    @Size(max = 64)
    private String userKey;

    /**
     * 浏览器客户端cookies
     */
    private String cookies;

    /**
     * 数据
     */
    @Size(max = 512)
    private String data;

    /**
     * 平台
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    private ActionOS os;

    /**
     * 渠道
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    private ActionChannel channel;

    /**
     * 渠道信息
     */
    @Size(max = 255)
    private String channelInfo;

    /**
     * 渠道版本
     */
    @Size(max = 16)
    private String channelVersion;

    /**
     * 访问IP
     */
    @Size(max = 16)
    private String userIp;

    /**
     * 运营商
     */
    @Size(max = 16)
    private String isp;

    /**
     * 国家
     */
    @Size(max = 32)
    private String country;

    /**
     * 省
     */
    @Size(max = 32)
    private String region;

    /**
     * 市
     */
    @Size(max = 32)
    private String city;

    /**
     * 经纬度
     */
    @Size(max = 32)
    private String lntLat;

    /**
     * 点击量
     */
    private Integer pv = 0;

    /**
     * 备注
     */
    @Size(max = 128)
    private String comments;

    /**
     * 日志时间（收集系统时间）
     */
    private Date actionTime = new Date();


    private String referer;

}
