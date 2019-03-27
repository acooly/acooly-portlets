/*
 * www.yiji.com Inc.
 * Copyright (c) 2016 All Rights Reserved
 */

/*
 * 修订记录:
 * qiubo@yiji.com 2016-10-27 23:31 创建
 */
package com.acooly.portlets.siteconfig;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author qiubo
 */
@ConfigurationProperties(SiteConfigProperties.PREFIX)
@Data
public class SiteConfigProperties {

    public static final String PREFIX = "acooly.siteconfig";
    /**
     * 是否启用
     */
    private boolean enable = true;

}
