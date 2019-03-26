/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.alog.core;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * 配置参数
 *
 * @author zhangpu
 * @date 2019-1-6
 */
@Getter
@Setter
@ConfigurationProperties(prefix = AlogProperties.PREFIX)
public class AlogProperties {
    public static final String PREFIX = "acooly.alog";
    /**
     * 是否启用该组件
     */
    private Boolean enable = true;
    /**
     * 前端portal：访问收集（JS）模式
     */
    private Boolean enablePortal = true;

    /**
     * 前端portal：允许被收集的网站的域名注册，多个逗号分隔。例如：http://acooly.cn,https://www.acooly.cn
     */
    private String allowOrigins;

    /**
     * session的userKey，可多个，直到找到不为空的值
     */
    private List<String> sessionUserKeys;

    /**
     * 本地缓存队列大小，满则丢弃，通过日志调整该值
     */
    private long cacheQueueSize = 10000;

    /**
     * 批量保存缓存大小
     */
    private int saveCacheSize = 100;

    /**
     * 批量保存定时检查间隔时间
     */
    private int savePeriodSeconds = 5;

}
