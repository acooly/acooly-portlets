/*
 * www.yiji.com Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * qiubo@yiji.com 2017-02-14 17:04 创建
 */
package com.acooly.portlets.siteconfig;

import com.acooly.core.common.dao.support.StandardDatabaseScriptIniter;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @author qiubo@yiji.com
 */
@Configuration
@EnableConfigurationProperties({SiteConfigProperties.class})
@ConditionalOnProperty(value = SiteConfigProperties.PREFIX + ".enable", matchIfMissing = true)
@ComponentScan(basePackages = "com.acooly.portlets.siteconfig")
public class SiteConfigAutoConfig {

    @Autowired
    private SiteConfigProperties portletProperties;

    /**
     * 数据库初始化
     *
     * @return
     */
    @Bean
    public StandardDatabaseScriptIniter PortletScriptIniter() {

        return new StandardDatabaseScriptIniter() {
            @Override
            public String getEvaluateTable() {
                return "portlet_site_config";
            }

            @Override
            public String getComponentName() {
                return "siteconfig";
            }

            @Override
            public List<String> getInitSqlFile() {
                return Lists.newArrayList("siteconfig", "siteconfig_urls");
            }
        };
    }

}
