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
import com.acooly.portlets.alog.analysis.persist.enums.AnalysisPeriod;
import com.acooly.portlets.alog.analysis.persist.service.ActionAnalysisVisitsService;
import com.acooly.portlets.alog.core.AlogProperties;
import io.jsonwebtoken.lang.Assert;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 访问量统计 Service实现
 * <p>
 * Date: 2019-03-23 16:38:18
 *
 * @author zhangpu@acooly.cn
 */
@Slf4j
@Service("actionAnalysisVisitsService")
public class ActionAnalysisVisitsServiceImpl extends EntityServiceImpl<ActionAnalysisVisits, ActionAnalysisVisitsDao> implements ActionAnalysisVisitsService {

    @Autowired
    private ActionVisitsDao actionVisitsDao;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private AlogProperties alogProperties;

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
        AnalysisPeriod analysisPeriod = AnalysisPeriod.find((String) map.get("EQ_analysisPeriod"));
        Assert.notNull(analysisPeriod, "查询条件:周期是必须的");
        Assert.hasLength((String) map.get("GTE_period"), "开始时间");
        Assert.hasLength((String) map.get("LTE_period"), "结束时间");
        if (analysisPeriod == AnalysisPeriod.HOUR) {
            String cacheKey = getCacheKey(map);
            ValueOperations<String, List<ActionVisitsInfo>> vo = redisTemplate.opsForValue();
            List<ActionVisitsInfo> actionVisitsInfos = vo.get(cacheKey);
            if (actionVisitsInfos == null) {
                actionVisitsInfos = actionVisitsDao.listRealTime(map, null);
                vo.set(cacheKey, actionVisitsInfos, alogProperties.getRealTimeVisitsCacheTimeoutMinutes(), TimeUnit.MINUTES);
                log.info("alog Real-time-visits analysis by hour from database,cacheMinutes:{}", alogProperties.getRealTimeVisitsCacheTimeoutMinutes());
            }
            return actionVisitsInfos;
        } else {
            return actionVisitsDao.list(map, null);
        }
    }

    private String getCacheKey(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("Alog_visits_realtime_").append(map.get("GTE_period"));
        return sb.toString();
    }
}
