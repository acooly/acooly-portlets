/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-18 23:06
 */
package com.acooly.portlets.alog.core.service;

import com.acooly.portlets.alog.core.dto.ActionLogInfo;

import javax.servlet.http.HttpServletRequest;

/**
 * Alog日志接口
 *
 * @author zhangpu
 * @date 2019-03-18 23:06
 */
public interface AlogService {

    /**
     * 通过DTO保存
     *
     * @param actionLogInfo
     */
    void log(ActionLogInfo actionLogInfo);

    /**
     * 根据Http请求解析并保持log
     *
     * @param request
     */
    void log(HttpServletRequest request);

    /**
     * DTO结合请求解析
     *
     * @param actionLogInfo
     * @param request
     */
    void log(ActionLogInfo actionLogInfo, HttpServletRequest request);

}
