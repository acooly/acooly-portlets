/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-02-14 17:04 创建
 */
package com.acooly.portlets.feedback.core;

import com.acooly.core.common.dao.support.StandardDatabaseScriptIniter;
import com.acooly.portlets.feedback.client.facade.api.FeedbackFacade;
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
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.List;

import static com.acooly.portlets.feedback.core.PortletFeedbackProperties.PREFIX;


/**
 * @author kuli@yiji.com
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({PortletFeedbackProperties.class})
@ConditionalOnProperty(value = PREFIX + ".enable", matchIfMissing = true)
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


    @Bean
    @ConditionalOnProperty(value = PREFIX + ".facadeEnable", matchIfMissing = false, havingValue = "true")
    @ConditionalOnBean(ProtocolConfig.class)
    @DependsOn({"applicationConfig", "registryConfig", "protocolConfig"})
    public static ServiceConfig<FeedbackFacade> feedbackFacadeConfig(ApplicationConfig applicationConfig, RegistryConfig registryConfig,
                                                                     ProtocolConfig protocolConfig, FeedbackFacade feedbackFacade) {
        ServiceConfig<FeedbackFacade> service = new ServiceConfig<FeedbackFacade>();
        service.setApplication(applicationConfig);
        service.setRegistry(registryConfig);
        service.setProtocol(protocolConfig);
        service.setInterface(FeedbackFacade.class);
        service.setRef(feedbackFacade);
        service.setVersion("1.0");
        service.export();
        return service;
    }


}
