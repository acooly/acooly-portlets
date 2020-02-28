/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-02-14 17:04 创建
 */
package com.acooly.portlets.fqa.core;

import com.acooly.core.common.dao.support.StandardDatabaseScriptIniter;
import com.acooly.portlets.fqa.facade.api.FqaRemoteService;
import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.ServiceConfig;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.List;

import static com.acooly.portlets.fqa.core.PortletFqaProperties.PREFIX;


/**
 * @author kuli@yiji.com
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({PortletFqaProperties.class})
@ConditionalOnProperty(value = PREFIX + ".enable", matchIfMissing = true)
@ComponentScan(basePackages = {"com.acooly.portlets.fqa"})
@MapperScan("com.acooly.portlets.fqa.core.dao")
public class PortletFqaAutoConfig {
    @Autowired
    private PortletFqaProperties portletFeedbackProperties;


    @Bean
    public StandardDatabaseScriptIniter portletFqaScriptIniter() {
        return new StandardDatabaseScriptIniter() {
            @Override
            public String getEvaluateTable() {
                return "p_fqa";
            }

            @Override
            public String getComponentName() {
                return "fqa";
            }

            @Override
            public List<String> getInitSqlFile() {
                return Lists.newArrayList("ddl", "init");
            }
        };
    }


    @Bean
    @ConditionalOnProperty(value = PREFIX + ".facadeEnable", matchIfMissing = false, havingValue = "true")
    @ConditionalOnBean(ProtocolConfig.class)
    @DependsOn({"applicationConfig", "registryConfig", "protocolConfig"})
    public static ServiceConfig<FqaRemoteService> feedbackFacadeConfig(ApplicationConfig applicationConfig, RegistryConfig registryConfig,
                                                                       ProtocolConfig protocolConfig, FqaRemoteService fqaRemoteService) {
        ServiceConfig<FqaRemoteService> service = new ServiceConfig<FqaRemoteService>();
        service.setApplication(applicationConfig);
        service.setRegistry(registryConfig);
        service.setProtocol(protocolConfig);
        service.setInterface(FqaRemoteService.class);
        service.setRef(fqaRemoteService);
        service.setVersion("1.0");
        service.export();
        return service;
    }


}
