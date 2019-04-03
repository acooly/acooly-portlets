/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-03-23
 *
 */
package com.acooly.portlets.alog.analysis.persist.service;

import com.acooly.core.common.service.EntityService;
import com.acooly.portlets.alog.analysis.persist.dto.ActionVisitsInfo;
import com.acooly.portlets.alog.analysis.persist.entity.ActionAnalysisVisits;

import java.util.List;
import java.util.Map;

/**
 * 访问量统计 Service接口
 * <p>
 * Date: 2019-03-23 16:38:18
 *
 * @author zhangpu@acooly.cn
 */
public interface ActionAnalysisVisitsService extends EntityService<ActionAnalysisVisits> {

    ActionAnalysisVisits getUnique(String period);

    List<ActionVisitsInfo> list(Map<String, Object> map);

    void cacheClear(String period);


}
