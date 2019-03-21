/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-05 22:57
 */
package com.acooly.portlets.alog.core.dto;

import com.acooly.portlets.alog.core.enums.ActionChannel;
import com.acooly.portlets.alog.core.enums.ActionOS;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Size;

/**
 * @author zhangpu
 * @date 2019-03-05 22:57
 */
@Data
public class ActionLogInfo {

    /**
     * 操作
     */
    @NotEmpty
    @Size(max = 255)
    private String actionKey;

    @Size(max = 64)
    private String actionGroup;

    /**
     * 操作名称
     */
    @Size(max = 32)
    private String actionName;

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
     * 平台
     */
    @Enumerated(EnumType.STRING)
    private ActionOS os;

    /**
     * 渠道
     */
    @Enumerated(EnumType.STRING)
    private ActionChannel channel;

    /**
     * 数据
     */
    @Size(max = 512)
    private String data;

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

}
