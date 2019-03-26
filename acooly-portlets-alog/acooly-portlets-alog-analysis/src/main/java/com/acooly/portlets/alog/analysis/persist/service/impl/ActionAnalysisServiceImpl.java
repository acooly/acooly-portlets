/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-23 16:56
 */
package com.acooly.portlets.alog.analysis.persist.service.impl;

import com.acooly.core.utils.Dates;
import com.acooly.portlets.alog.analysis.persist.dao.ActionVisitsDao;
import com.acooly.portlets.alog.analysis.persist.entity.ActionAnalysisVisits;
import com.acooly.portlets.alog.analysis.persist.enums.ActionVisitsTypeEnum;
import com.acooly.portlets.alog.analysis.persist.service.ActionAnalysisService;
import com.acooly.portlets.alog.analysis.persist.service.ActionAnalysisVisitsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author zhangpu
 * @date 2019-03-23 16:56
 */
@Slf4j
@Component
public class ActionAnalysisServiceImpl implements ActionAnalysisService {

    @Autowired
    private ActionVisitsDao actionVisitsDao;

    @Autowired
    private ActionAnalysisVisitsService actionAnalysisVisitsService;

    @Override
    public void analysisVisitsByDay(Date day) {
        String period = Dates.format(day);
        try {
            ActionAnalysisVisits aav = actionAnalysisVisitsService.getUnique(period);
            if (aav != null) {
                log.warn("alog 访问量统计 已统计，直接退出 day:{}", period);
                return;
            }
            int pv = doAnalysisQuery(day, ActionVisitsTypeEnum.PV);
            int uv = doAnalysisQuery(day, ActionVisitsTypeEnum.UV);
            int ip = doAnalysisQuery(day, ActionVisitsTypeEnum.IP);
            aav = new ActionAnalysisVisits(period, pv, uv, ip);
            actionAnalysisVisitsService.saveOrUpdate(aav);
            log.warn("alog 访问量统计 成功 ActionAnalysisVisits:{}", aav);
        } catch (Exception e) {
            log.warn("alog 访问量统计 失败 day:{}", period, e);
        }
    }


    protected int doAnalysisQuery(Date date, ActionVisitsTypeEnum type) {
        if (type == ActionVisitsTypeEnum.PV) {
            return actionVisitsDao.pv(date);
        } else if (type == ActionVisitsTypeEnum.UV) {
            return actionVisitsDao.uv(date);
        } else {
            return actionVisitsDao.ip(date);
        }
    }
}
