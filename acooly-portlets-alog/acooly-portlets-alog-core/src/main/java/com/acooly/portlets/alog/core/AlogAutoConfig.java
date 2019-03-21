/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-02-14 17:04 创建
 */
package com.acooly.portlets.alog.core;

import com.acooly.core.common.dao.support.StandardDatabaseScriptIniter;
import com.google.common.collect.Lists;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.acooly.portlets.alog.core.AlogProperties.PREFIX;


/**
 * @author kuli@yiji.com
 */
@Configuration
@EnableConfigurationProperties({AlogProperties.class})
@ConditionalOnProperty(value = PREFIX + ".enable", matchIfMissing = true)
@ComponentScan(basePackages = {"com.acooly.portlets.alog.core"})
@MapperScan("com.acooly.portlets.alog.core.dao")
public class AlogAutoConfig {
    @Autowired
    private AlogProperties alogProperties;

    @Bean
    public StandardDatabaseScriptIniter portletAlogScriptIniter() {
        return new StandardDatabaseScriptIniter() {
            @Override
            public String getEvaluateTable() {
                return "p_action_log";
            }

            @Override
            public String getComponentName() {
                return "alog";
            }

            @Override
            public List<String> getInitSqlFile() {
                return Lists.newArrayList("alog_init", "alog_resource");
            }
        };
    }
}
