/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-18 14:43
 */
package com.acooly.portlets.alog.core.service.impl;

import com.acooly.core.utils.Collections3;
import com.acooly.core.utils.Servlets;
import com.acooly.portlets.alog.core.AlogProperties;
import com.acooly.portlets.alog.core.service.ActionLogUserKeyParser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @author zhangpu
 * @date 2019-03-18 14:43
 */
@Slf4j
@Component
public class ActionLogUserKeySessionParser implements ActionLogUserKeyParser {

    @Autowired
    private AlogProperties alogProperties;

    @Override
    public String parseUserKey(HttpServletRequest request) {

        List<String> sessionKeys = alogProperties.getSessionUserKeys();
        if (Collections3.isEmpty(sessionKeys)) {
            return null;
        }

        Object userKey = null;
        for (String sessionKey : sessionKeys) {
            userKey = Servlets.getSessionAttribute(sessionKey);
            if (userKey != null) {
                return userKey.toString();
            }
        }
        return null;
    }
}
