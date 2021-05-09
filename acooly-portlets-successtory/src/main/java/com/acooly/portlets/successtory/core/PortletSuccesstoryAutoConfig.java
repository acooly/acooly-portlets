/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-02-14 17:04 创建
 */
package com.acooly.portlets.successtory.core;

import com.acooly.core.common.dao.support.StandardDatabaseScriptIniter;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.acooly.portlets.successtory.core.PortletSuccesstoryProperties.PREFIX;


/**
 * @author kuli@yiji.com
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({PortletSuccesstoryProperties.class})
@ConditionalOnProperty(value = PREFIX + ".enable", matchIfMissing = true)
public class PortletSuccesstoryAutoConfig {

    @Autowired
    private PortletSuccesstoryProperties portletSuccesstoryProperties;

    @Bean
    public StandardDatabaseScriptIniter portletSuccesstoryScriptIniter() {
        return new StandardDatabaseScriptIniter() {
            @Override
            public String getEvaluateTable() {
                return "p_success_story";
            }

            @Override
            public String getComponentName() {
                return PortletSuccesstoryProperties.COMPONENT_NAME;
            }

            @Override
            public List<String> getInitSqlFile() {
                return Lists.newArrayList("ddl", "init");
            }
        };
    }


}
