/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-01-10
 *
 */
package com.acooly.portlets.alog.core.service;

import com.acooly.core.common.service.EntityService;
import com.acooly.portlets.alog.core.entity.ActionMapping;

/**
 * 访问映射 Service接口
 * <p>
 * Date: 2019-01-10 18:10:55
 *
 * @author zhangpu@acooly.cn
 */
public interface ActionMappingService extends EntityService<ActionMapping> {

    ActionMapping getActionMapping(String actionKey);

}
