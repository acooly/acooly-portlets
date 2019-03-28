/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-02-14 17:04 创建
 */
package com.acooly.portlets.alog.openapi;

import com.acooly.portlets.alog.core.AlogProperties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static com.acooly.portlets.alog.core.AlogProperties.PREFIX;


/**
 * @author zhangpu
 */
@Configuration
@EnableConfigurationProperties({AlogProperties.class})
@ConditionalOnProperty(value = PREFIX + ".enable", matchIfMissing = true)
@ComponentScan(basePackages = {"com.acooly.portlets.alog.openapi"})
public class AlogOpenApiAutoConfig {

}
