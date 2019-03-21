/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-01-10
 */
package com.acooly.portlets.alog.core.service.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.core.utils.system.IPUtil;
import com.acooly.portlets.alog.core.dao.ActionLogDao;
import com.acooly.portlets.alog.core.dto.ActionLogInfo;
import com.acooly.portlets.alog.core.entity.ActionLog;
import com.acooly.portlets.alog.core.entity.ActionMapping;
import com.acooly.portlets.alog.core.enums.ActionChannel;
import com.acooly.portlets.alog.core.enums.ActionOS;
import com.acooly.portlets.alog.core.service.ActionLogService;
import com.acooly.portlets.alog.core.service.ActionLogUserKeyParser;
import com.acooly.portlets.alog.core.service.ActionMappingService;
import eu.bitwalker.useragentutils.Browser;
import eu.bitwalker.useragentutils.BrowserType;
import eu.bitwalker.useragentutils.OperatingSystem;
import eu.bitwalker.useragentutils.UserAgent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * 访问日志 Service实现
 * <p>
 * Date: 2019-01-10 18:10:55
 *
 * @author zhangpu@acooly.cn
 */
@Service("actionLogService")
public class ActionLogServiceImpl extends EntityServiceImpl<ActionLog, ActionLogDao> implements ActionLogService {


}
