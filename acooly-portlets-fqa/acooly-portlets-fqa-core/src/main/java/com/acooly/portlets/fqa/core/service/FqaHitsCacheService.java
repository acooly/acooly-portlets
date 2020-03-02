/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-19 00:17
 */
package com.acooly.portlets.fqa.core.service;

import com.acooly.core.utils.ShutdownHooks;
import com.acooly.portlets.fqa.core.PortletFqaProperties;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangpu
 * @date 2019-03-19 00:17
 */
@Slf4j
//@Component
public class FqaHitsCacheService implements InitializingBean {

    @Autowired
    private FqaService fqaService;

    @Autowired
    private PortletFqaProperties portletFqaProperties;


    private Map<Long, Integer> caches = Maps.newConcurrentMap();

    private void init() {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        executor.scheduleAtFixedRate(() -> {
            consume();
        }, 1, portletFqaProperties.getHitCache().getSavePeriodSeconds(), TimeUnit.SECONDS);
        ShutdownHooks.addShutdownHook(() -> {
            try {
                executor.shutdown();
            } catch (Exception e) {
                //
            }
            log.info("FQA-Hits 消费线程关闭...");
        }, "FQA-Hits 关闭消费线程");
        log.info("FQA-Hits 消费队列和线程初始化完成...");
    }

    public void produce(Long id) {
        if (caches.get(id) == null) {
            caches.put(id, 0);
        }
        caches.put(id, caches.get(id) + 1);
        log.debug("FQA-Hits Id:{}, count:{}", id, caches.get(id));
    }

    public void consume() {
        try {
            caches.clear();
//            fqaService.saves(actionLogs);
//            log.info("FQA-Hits 批量消费写入数据库成功。batchSize:{},queueSize:{}", actionLogs.size(), queue.size());
        } catch (Exception e) {
            log.warn("FQA-Hits 批量消费写入数据库失败。", e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
