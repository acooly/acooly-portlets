/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-23 17:30
 */
package com.acooly.portlets.alog.analysis.persist.dao;

import com.acooly.core.common.dao.DynamicListQueryDao;
import com.acooly.portlets.alog.analysis.persist.dto.ActionVisitsInfo;

import java.util.Date;

/**
 * @author zhangpu
 * @date 2019-03-23 17:30
 */
public interface ActionVisitsDao extends DynamicListQueryDao<ActionVisitsInfo> {

    int pv(Date date);

    int uv(Date date);

    int ip(Date date);

}
