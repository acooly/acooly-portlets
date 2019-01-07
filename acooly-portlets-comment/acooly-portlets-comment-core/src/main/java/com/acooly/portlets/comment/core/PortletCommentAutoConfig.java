/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-02-14 17:04 创建
 */
package com.acooly.portlets.comment.core;

import com.acooly.core.common.dao.support.StandardDatabaseScriptIniter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.acooly.portlets.comment.core.PortletCommentProperties.PREFIX;

;

/**
 * @author kuli@yiji.com
 */
@Configuration
@EnableConfigurationProperties({PortletCommentProperties.class})
@ConditionalOnProperty(value = PREFIX + ".enable", matchIfMissing = true)
@ComponentScan(basePackages = {"com.acooly.portlets.comment"})
public class PortletCommentAutoConfig {
    @Autowired
    private PortletCommentProperties portletCommentProperties;

    @Bean
    public StandardDatabaseScriptIniter appScriptIniter() {
        return new StandardDatabaseScriptIniter() {
            @Override
            public String getEvaluateTable() {
                return "p_comment";
            }

            @Override
            public String getComponentName() {
                return "comment";
            }

            @Override
            public List<String> getInitSqlFile() {
                return Lists.newArrayList("ddl", "init");
            }
        };
    }
}
