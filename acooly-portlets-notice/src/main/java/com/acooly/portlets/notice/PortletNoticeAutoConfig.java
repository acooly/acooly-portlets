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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static com.acooly.portlets.notice.PortletNoticeProperties.PREFIX;

import java.util.List;

;

/**
 * @author kuli@yiji.com
 */
@Configuration
@EnableConfigurationProperties({PortletNoticeProperties.class})
@ConditionalOnProperty(value = PREFIX + ".enable", matchIfMissing = true)
@ComponentScan(basePackages = "com.acooly.portlets.notice")
public class PortletNoticeAutoConfig {
	@Autowired
	private PortletNoticeProperties noticeProperties;
	
	@Bean
	public StandardDatabaseScriptIniter appScriptIniter () {
		return new StandardDatabaseScriptIniter () {
			@Override
			public String getEvaluateTable () {
				return "app_banner";
			}
			
			@Override
			public String getComponentName () {
				return "app";
			}
			
			@Override
			public List<String> getInitSqlFile () {
				return Lists.newArrayList ("app", "app_urls");
			}
		};
	}
}
