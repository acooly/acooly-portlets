/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.successtory.core;

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
@ConfigurationProperties(prefix = PortletSuccesstoryProperties.PREFIX)
public class PortletSuccesstoryProperties {
    public static final String PREFIX = "acooly.portlets.successtory";
    public static final String COMPONENT_NAME = "successtory";
    /**
     * 是否启用该组件
     */
    private Boolean enable = true;


}
