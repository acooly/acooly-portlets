/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-19 00:17
 */
package com.acooly.portlets.alog.core.service;

import com.acooly.core.utils.Collections3;
import com.acooly.core.utils.ShutdownHooks;
import com.acooly.portlets.alog.core.AlogProperties;
import com.acooly.portlets.alog.core.entity.ActionLog;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangpu
 * @date 2019-03-19 00:17
 */
@Slf4j
@Component
public class AlogCacheService implements InitializingBean {

    @Autowired
    private ActionLogService actionLogService;

    @Autowired
    private AlogProperties alogProperties;

    private BlockingQueue<ActionLog> queue = null;


    private void init() {
        queue = new ArrayBlockingQueue<>(alogProperties.getSaveCacheSize());

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);
        executor.scheduleAtFixedRate(() -> {
            consume();
        }, 1, alogProperties.getSavePeriodSeconds(), TimeUnit.SECONDS);
        ShutdownHooks.addShutdownHook(() -> {
            try {
                executor.shutdown();
            } catch (Exception e) {
                //
            }
            log.info("alog 消费线程关闭...");
        }, "关闭alog消费线程");
        log.info("alog 消费队列和线程初始化完成...");
    }

    public void produce(ActionLog actionLog) {
        try {
            queue.offer(actionLog, 1, TimeUnit.SECONDS);
            log.debug("alog入队:{}", actionLog);
        } catch (Exception e) {
            log.warn("alog队列满，丢弃本条日志。data:{}", actionLog);
        }
    }

    public void consume() {

        try {
            List<ActionLog> actionLogs = Lists.newArrayList();
            queue.drainTo(actionLogs, alogProperties.getSaveCacheSize());
            if (Collections3.isEmpty(actionLogs)) {
                return;
            }
            actionLogService.saves(actionLogs);
            log.info("alog 批量消费写入数据库成功。batchSize:{},queueSize:{}", actionLogs.size(), queue.size());
        } catch (Exception e) {
            log.warn("alog 批量消费写入数据库失败。", e);
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }
}
