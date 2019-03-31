/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-02-16 17:04 创建
 */
package com.acooly.portlets.alog.core;

import com.acooly.core.common.boot.component.ComponentInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author kuli@yiji.com
 */
@Slf4j
public class AlogComponentInitializer implements ComponentInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        setPropertyIfMissing("acooly.security.csrf.exclusions.alog[0]", "/acooly/alog/log.html");

        /** 升级支持来源 referer */
        setPropertyIfMissing("acooly.ds.dbPatchs.p_action_log[0].columnName", "referer");
        setPropertyIfMissing("acooly.ds.dbPatchs.p_action_log[0].patchSql", "ALTER TABLE `p_action_log` ADD COLUMN `referer` VARCHAR(128) NULL COMMENT '来源';");
    }
}
