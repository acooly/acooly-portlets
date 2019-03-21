/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.feedback.core;

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
@ConfigurationProperties(prefix = PortletFeedbackProperties.PREFIX)
public class PortletFeedbackProperties {
    public static final String PREFIX = "acooly.portlets.feedback";
    /**
     * 是否启用该组件
     */
    private Boolean enable = true;

    /**
     * 是否发送反馈相关状态的事件（提交和处理）
     */
    private boolean eventPublishEnable = false;

    private boolean facadeEnable = false;


}
