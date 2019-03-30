/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-18 23:06
 */
package com.acooly.portlets.alog.core.service.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.utils.Collections3;
import com.acooly.core.utils.Servlets;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.core.utils.mapper.JsonMapper;
import com.acooly.core.utils.system.IPUtil;
import com.acooly.portlets.alog.client.enums.ActionChannel;
import com.acooly.portlets.alog.client.enums.ActionOS;
import com.acooly.portlets.alog.core.AlogProperties;
import com.acooly.portlets.alog.core.dto.ActionLogInfo;
import com.acooly.portlets.alog.core.entity.ActionLog;
import com.acooly.portlets.alog.core.entity.ActionMapping;
import com.acooly.portlets.alog.core.service.ActionLogUserKeyParser;
import com.acooly.portlets.alog.core.service.ActionMappingService;
import com.acooly.portlets.alog.core.service.AlogCacheService;
import com.acooly.portlets.alog.core.service.AlogService;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.net.HttpHeaders;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.BrowserType;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.net.URL;
import java.util.List;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2019-03-18 23:06
 */
@Slf4j
@Component
public class AlogServiceImpl implements AlogService {

    private static final int MAX_DATA_LENGTH = 512;

    @Autowired
    private ActionMappingService actionMappingService;
    @Autowired
    private ActionLogUserKeyParser actionLogUserKeyParser;
    @Autowired
    private AlogCacheService alogCacheService;
    @Autowired
    private AlogProperties alogProperties;


    @Override
    public void log(ActionLogInfo actionLogInfo) {
        try {
            ActionLog actionLog = new ActionLog();
            BeanCopier.copy(actionLogInfo, actionLog);
            doLogMapping(actionLog, null);
            alogCacheService.produce(actionLog);
        } catch (BusinessException be) {
            throw be;
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCodes.INTERNAL_ERROR);
        }
    }

    @Override
    public void log(HttpServletRequest request) {
        try {
            ActionLog actionLog = new ActionLog();
            doLogParse(actionLog, request);
            doLogMapping(actionLog, request);
            alogCacheService.produce(actionLog);
        } catch (BusinessException be) {
            throw be;
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCodes.INTERNAL_ERROR);
        }
    }

    @Override
    public void log(ActionLogInfo actionLogInfo, HttpServletRequest request) {

        try {
            ActionLog actionLog = new ActionLog();
            BeanCopier.copy(actionLogInfo, actionLog);
            doLogParse(actionLog, request);
            doLogMapping(actionLog, request);
            alogCacheService.produce(actionLog);
        } catch (BusinessException be) {
            throw be;
        } catch (Exception e) {
            throw new BusinessException(CommonErrorCodes.INTERNAL_ERROR);
        }

    }

    /**
     * 从Request解析ActionLog
     *
     * @param actionLog
     * @param request
     */
    protected void doLogParse(ActionLog actionLog, HttpServletRequest request) {
        String requestUrl = request.getRequestURI();
        actionLog.setActionKey(Strings.isBlankDefault(actionLog.getActionKey(), requestUrl));
        actionLog.setActionUrl(Strings.isBlankDefault(actionLog.getActionUrl(), requestUrl));
        parseChannelInfo(actionLog, request);
        parseUserKey(actionLog, request);
        parseReferer(actionLog, request);
    }


    /**
     * 日志mapping
     *
     * @param actionLog
     */
    protected void doLogMapping(ActionLog actionLog, HttpServletRequest request) {
        if (Strings.isBlank(actionLog.getActionKey())) {
            return;
        }
        ActionMapping actionMapping = actionMappingService.getActionMapping(actionLog.getActionKey());
        if (actionMapping == null) {
            return;
        }

        actionLog.setActionName(actionMapping.getActionName());
        actionLog.setActionGroup(actionMapping.getActionGroup());
        String params = actionMapping.getParams();
        if (Strings.isNotBlank(params) && request != null) {
            Map data = Maps.newLinkedHashMap();
            List<String> parameters = Lists.newArrayList(params.split(","));
            if (parameters.contains("*")) {
                data = Servlets.getParameters(request, null, false);
            } else {
                String paramValue = null;
                for (String key : parameters) {

                    if (Strings.endsWith(key, "*") && Strings.length(key) > 1) {
                        String prefixPart = Strings.substringBefore(key, "*");
                        data.putAll(Servlets.getParameters(request, prefixPart, false));
                    } else {
                        paramValue = Servlets.getParameter(request, key);
                        if (Strings.isNotBlank(paramValue)) {
                            data.put(key, paramValue);
                        }
                    }
                }
            }

            if (data.isEmpty()) {
                return;
            }

            Object lastKey = null;
            Object lastValue = null;
            String jsonData = JsonMapper.nonEmptyMapper().toJson(data);
            while (Strings.length(jsonData) > MAX_DATA_LENGTH) {
                lastKey = Collections3.getLast(data.keySet());
                lastValue = data.remove(lastKey);
                log.debug("最大：{}，丢弃: {}:{}", MAX_DATA_LENGTH, lastKey, lastValue);
                jsonData = JsonMapper.nonEmptyMapper().toJson(data);
            }
            log.debug("alog 收集配置参数: {}", jsonData);
        }

    }


    /**
     * 解析用户
     *
     * @param actionLog
     * @param request
     */
    protected void parseUserKey(ActionLog actionLog, HttpServletRequest request) {
        if (Strings.isBlank(actionLog.getUserKey())) {
            actionLog.setUserKey(actionLogUserKeyParser.parseUserKey(request));
        }

        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (alogProperties.getSessionIdKeys().contains(cookie.getName())) {
                    actionLog.setCookies(cookie.getValue());
                }
            }
        }
        if (Strings.isBlank(actionLog.getCookies())) {
            actionLog.setCookies(actionLog.getUserKey());
        }
    }

    protected void parseReferer(ActionLog actionLog, HttpServletRequest request) {
        String referer = request.getHeader(HttpHeaders.REFERER);
        if (Strings.isNotBlank(referer)) {
            try {
                URL url = new URL(referer);
                actionLog.setReferer(url.getHost());
            } catch (Exception e) {
                //ig
            }
        }
    }

    /**
     * 解析渠道信息
     *
     * @param actionLog
     * @param request
     */
    protected void parseChannelInfo(ActionLog actionLog, HttpServletRequest request) {
        if (request == null) {
            return;
        }
        String userAgentString = request.getHeader("User-Agent");
        UserAgent userAgent = UserAgent.parseUserAgentString(userAgentString);
        OperatingSystem os = userAgent.getOperatingSystem();
        Browser browser = userAgent.getBrowser();
        // 操作系统（设备类型）
        if (actionLog.getOs() == null) {
            actionLog.setOs(doParseActionOS(userAgent));
        }

        // 渠道类型
        if (actionLog.getChannel() == null) {
            actionLog.setChannel(doParseChannel(userAgent, userAgentString));
            StringBuilder channelInfo = new StringBuilder();
            channelInfo.append(os.getName()).append("-")
                    .append(browser.getName()).append("-")
                    .append(browser.getBrowserType().getName());
            actionLog.setChannelInfo(channelInfo.toString());
            actionLog.setChannelVersion(userAgent.getBrowserVersion().getVersion());
        }
        // 请求IP
        if (Strings.isBlank(actionLog.getUserIp())) {
            actionLog.setUserIp(IPUtil.getIpAddr(request));
        }

    }


    /**
     * 解析OS（设备）
     *
     * @param userAgent
     * @return
     */
    protected ActionOS doParseActionOS(UserAgent userAgent) {
        ActionOS marchActionOS = ActionOS.OTHER;
        for (ActionOS actionOS : ActionOS.getAll()) {
            if (Strings.containsIgnoreCase(userAgent.getOperatingSystem().getName(), actionOS.code())) {
                marchActionOS = actionOS;
            }
        }
        return marchActionOS;
    }

    /**
     * 解析渠道（访问软件）
     *
     * @param userAgent
     * @param userAgentString
     * @return
     */
    protected ActionChannel doParseChannel(UserAgent userAgent, String userAgentString) {

        if (isMicroMessenger(userAgentString)) {
            return ActionChannel.MicroMessenger;
        } else if (isMiniProgram(userAgentString)) {
            return ActionChannel.MiniProgram;
        } else if (userAgent.getBrowser().getBrowserType() == BrowserType.APP) {
            return ActionChannel.App;
        } else if (userAgent.getBrowser().getBrowserType() == BrowserType.WEB_BROWSER) {
            return ActionChannel.Web;
        } else if (userAgent.getBrowser().getBrowserType() == BrowserType.MOBILE_BROWSER) {
            return ActionChannel.H5;
        } else {
            return ActionChannel.Other;
        }
    }

    private boolean isMicroMessenger(String userAgentString) {
        return Strings.contains(userAgentString, ActionChannel.MicroMessenger.code());
    }

    private boolean isMiniProgram(String userAgentString) {
        return Strings.contains(userAgentString, ActionChannel.MiniProgram.code());
    }

}
