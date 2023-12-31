/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-02-14 17:04 创建
 */
package com.acooly.portlets.notice;

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

import static com.acooly.portlets.notice.PortletNoticeProperties.PREFIX;

;

/**
 * @author kuli@yiji.com
 */
@Configuration
@EnableConfigurationProperties({PortletNoticeProperties.class})
@ConditionalOnProperty(value = PREFIX + ".enable", matchIfMissing = true)
@ComponentScan(basePackages = {"com.acooly.portlets.notice"})
@MapperScan("com.acooly.portlets.notice.core.dao")
public class PortletNoticeAutoConfig {
    @Autowired
    private PortletNoticeProperties noticeProperties;

    @Bean
    public StandardDatabaseScriptIniter noticeScriptIniter() {
        return new StandardDatabaseScriptIniter() {
            @Override
            public String getEvaluateTable() {
                return "p_notice_info";
            }

            @Override
            public String getComponentName() {
                return "notice";
            }

            @Override
            public List<String> getInitSqlFile() {
                return Lists.newArrayList("portlets_notice_init", "portlets_notice_init_urls");
            }
        };
    }
}
