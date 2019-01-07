/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.comment.core;

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
@ConfigurationProperties(prefix = PortletCommentProperties.PREFIX)
public class PortletCommentProperties {
    public static final String PREFIX = "acooly.portlets.comment";
    /**
     * 是否启用该组件
     */
    private Boolean enable = true;


}
