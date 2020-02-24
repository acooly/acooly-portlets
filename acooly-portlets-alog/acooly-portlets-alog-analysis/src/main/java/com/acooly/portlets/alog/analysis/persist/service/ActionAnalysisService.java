/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-23 16:52
 */
package com.acooly.portlets.alog.analysis.persist.service;

import com.acooly.core.utils.Dates;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangpu
 * @date 2019-03-23 16:52
 */
public interface ActionAnalysisService {


    /**
     * 统计分析访问量
     * 包括：PV,UV,IP
     *
     * @param day
     */
    void analysisVisitsByDay(Date day);

    /**
     * 统计分析当天的访问量
     */
    default void analysisVisitsByDay() {
        analysisVisitsByDay(new Date());
    }

    /**
     * 统计昨天的访问量
     */
    default void analysisVisitsByYesterday() {
        analysisVisitsByDay(Dates.subDate(new Date(), 1, TimeUnit.DAYS));
    }

}
