/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-18 14:29
 */
package com.acooly.portlets.alog.core.service;

import javax.servlet.http.HttpServletRequest;

/**
 * @author zhangpu
 * @date 2019-03-18 14:29
 */
public interface ActionLogUserKeyParser {

    /**
     * 解析userKey
     *
     * @param request
     * @return
     */
    String parseUserKey(HttpServletRequest request);
}
