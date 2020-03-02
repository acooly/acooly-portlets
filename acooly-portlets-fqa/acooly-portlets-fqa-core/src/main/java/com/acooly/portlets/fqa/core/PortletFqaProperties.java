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
    public static final String TREE_TYPE_SCHEME_FQA = "FQA";
    /**
     * 是否启用该组件
     */
    private Boolean enable = true;
    /**
     * 是否启用facade远程服务
     */
    private boolean facadeEnable = false;

    /**
     * 点击批写缓存
     */
//    private HitCache hitCache = new HitCache();

//    /**
//     * 点击批写缓存
//     * 非精准缓存
//     */
//    @Getter
//    @Setter
//    public static class HitCache {
//        /**
//         * 本地缓存队列大小，满则丢弃，通过日志调整该值
//         */
//        private long cacheQueueSize = 1000;
//
//        /**
//         * 批量保存缓存大小
//         */
//        private int saveBatchSize = 10;
//
//        /**
//         * 批量保存定时检查间隔时间
//         */
//        private int savePeriodSeconds = 5;
//    }

}
