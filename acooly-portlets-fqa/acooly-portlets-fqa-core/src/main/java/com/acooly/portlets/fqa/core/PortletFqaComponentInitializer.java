/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-02-16 17:04 创建
 */
package com.acooly.portlets.fqa.core;

import com.acooly.core.common.boot.component.ComponentInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author kuli@yiji.com
 */
@Slf4j
public class PortletFqaComponentInitializer implements ComponentInitializer {


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        setPropertyIfMissing("acooly.security.xss.exclusions.faq[0]", "/manage/portlets/fqa/fqa/**");
    }
}
