/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.fqa.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 评论组件配置参数
 *
 * @author zhangpu
 * @date 2019-1-6
 */
@Getter
@Setter
@ConfigurationProperties(prefix = PortletFqaProperties.PREFIX)
public class PortletFqaProperties {
    public static final String PREFIX = "acooly.portlets.fqa";
    /**
     * 是否启用该组件
     */
    private Boolean enable = true;
    /**
     * 是否启用facade远程服务
     */
    private boolean facadeEnable = false;


}
