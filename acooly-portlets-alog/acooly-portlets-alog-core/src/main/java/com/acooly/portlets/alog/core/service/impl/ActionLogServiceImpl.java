/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-01-10
 */
package com.acooly.portlets.alog.core.service.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.portlets.alog.core.dao.ActionLogDao;
import com.acooly.portlets.alog.core.entity.ActionLog;
import com.acooly.portlets.alog.core.service.ActionLogService;
import org.springframework.stereotype.Service;

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
