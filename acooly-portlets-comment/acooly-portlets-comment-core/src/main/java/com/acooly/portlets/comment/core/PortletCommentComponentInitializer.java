/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

/*
 * 修订记录:
 * kuli@yiji.com 2017-02-16 17:04 创建
 */
package com.acooly.portlets.comment.core;

import com.acooly.core.common.boot.component.ComponentInitializer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author kuli@yiji.com
 */
@Slf4j
public class PortletCommentComponentInitializer implements ComponentInitializer {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

        // 升级comment表，增加附件梳理和星级两个字段
        setPropertyIfMissing("acooly.ds.dbPatchs.p_comment[0].columnName", "attach_count");
        setPropertyIfMissing("acooly.ds.dbPatchs.p_comment[0].patchSql", "ALTER TABLE `cms_content` ADD COLUMN `attach_count` int(11) DEFAULT '0' COMMENT '附件数量';");

        setPropertyIfMissing("acooly.ds.dbPatchs.p_comment[1].columnName", "star");
        setPropertyIfMissing("acooly.ds.dbPatchs.p_comment[1].patchSql", "ALTER TABLE `cms_content` ADD COLUMN `star` int(11) DEFAULT '0' COMMENT '星级';");

    }
}
