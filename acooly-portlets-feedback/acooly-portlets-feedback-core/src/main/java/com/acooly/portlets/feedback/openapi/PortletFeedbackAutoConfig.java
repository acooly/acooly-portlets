/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-02-14 17:04 创建
 */
package com.acooly.portlets.feedback.openapi;

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

import static com.acooly.portlets.feedback.openapi.PortletFeedbackProperties.PREFIX;

/**
 * @author kuli@yiji.com
 */
@Configuration
@EnableConfigurationProperties({PortletFeedbackProperties.class})
@ConditionalOnProperty(value = PREFIX + ".enable", matchIfMissing = true)
@ComponentScan(basePackages = {"com.acooly.portlets.feedback"})
@MapperScan("com.acooly.portlets.feedback.core.dao")
public class PortletFeedbackAutoConfig {
    @Autowired
    private PortletFeedbackProperties portletFeedbackProperties;

    @Bean
    public StandardDatabaseScriptIniter portletCommentScriptIniter() {
        return new StandardDatabaseScriptIniter() {
            @Override
            public String getEvaluateTable() {
                return "p_feedback";
            }

            @Override
            public String getComponentName() {
                return "feedback";
            }

            @Override
            public List<String> getInitSqlFile() {
                return Lists.newArrayList("ddl", "init");
            }
        };
    }
}
