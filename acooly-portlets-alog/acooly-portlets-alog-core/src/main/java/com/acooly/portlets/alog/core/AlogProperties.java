/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.alog.core;

import com.google.common.collect.Lists;
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
     * 打开openApi的执行完成事件监听收集日志
     */
    private Boolean openApiEnable = true;
    /**
     * OpenApi后置事件只收集来自移动App的访问
     */
    private Boolean openApiOnlyApp = true;

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
    private List<String> sessionUserKeys = Lists.newArrayList("USER_IN_SESSION", "SESSION_USER");

    /**
     * sessionId的key定义，被收集网站可能存在不同的sessionIdKey，比如：acooly框架的Web的sessionIdKey为：SESSION
     */
    private List<String> sessionIdKeys = Lists.newArrayList("SESSION", "JSESSIONID");

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
