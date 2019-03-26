/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-03-23
 */
package com.acooly.portlets.alog.analysis.persist.service.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Collections3;
import com.acooly.portlets.alog.analysis.persist.dao.ActionAnalysisVisitsDao;
import com.acooly.portlets.alog.analysis.persist.dao.ActionVisitsDao;
import com.acooly.portlets.alog.analysis.persist.dto.ActionVisitsInfo;
import com.acooly.portlets.alog.analysis.persist.entity.ActionAnalysisVisits;
import com.acooly.portlets.alog.analysis.persist.service.ActionAnalysisVisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 访问量统计 Service实现
 * <p>
 * Date: 2019-03-23 16:38:18
 *
 * @author zhangpu@acooly.cn
 */
@Service("actionAnalysisVisitsService")
public class ActionAnalysisVisitsServiceImpl extends EntityServiceImpl<ActionAnalysisVisits, ActionAnalysisVisitsDao> implements ActionAnalysisVisitsService {

    @Autowired
    private ActionVisitsDao actionVisitsDao;

    @Override
    public ActionAnalysisVisits getUnique(String period) {
        List<ActionAnalysisVisits> aavs = getEntityDao().findByActionDayAndType(period);
        if (Collections3.isNotEmpty(aavs)) {
            return Collections3.getFirst(aavs);
        }
        return null;
    }

    @Override
    public List<ActionVisitsInfo> list(Map<String, Object> map) {
        return actionVisitsDao.list(map, null);
    }
}
