/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-03-23
 */
package com.acooly.portlets.alog.analysis.persist.web;

import com.acooly.core.common.web.AbstractJsonEntityController;
import com.acooly.core.utils.Dates;
import com.acooly.core.utils.Servlets;
import com.acooly.core.utils.Strings;
import com.acooly.portlets.alog.analysis.persist.dto.ActionVisitsInfo;
import com.acooly.portlets.alog.analysis.persist.dto.AnalysisResult;
import com.acooly.portlets.alog.analysis.persist.entity.ActionAnalysisVisits;
import com.acooly.portlets.alog.analysis.persist.enums.AnalysisPeriod;
import com.acooly.portlets.alog.analysis.persist.service.ActionAnalysisVisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 访问量统计 管理控制器
 *
 * @author zhangpu@acooly.cn
 * Date: 2019-03-23 23:33:32
 */
@Controller
@RequestMapping(value = "/manage/alog/analysis")
public class ActionAnalysisManagerController extends AbstractJsonEntityController<ActionAnalysisVisits, ActionAnalysisVisitsService> {


    {
        allowMapping = "";
    }

    @SuppressWarnings("unused")
    @Autowired
    private ActionAnalysisVisitsService actionAnalysisVisitsService;

    /**
     * 访问量统计分析
     */
    @RequestMapping(path = "visits", method = RequestMethod.GET)
    public String visitsView(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAllAttributes(referenceData(request));
        String realTime = Servlets.getParameter(request, "realTime");
        if (Strings.isNotBlank(realTime)) {
            model.addAttribute("realTime", realTime);
        }
        return "/manage/alog/analysis/visits";
    }

    @RequestMapping(path = "visits", method = RequestMethod.POST)
    @ResponseBody
    public AnalysisResult<ActionVisitsInfo> visitsQuery(HttpServletRequest request, HttpServletResponse response) {
        AnalysisResult<ActionVisitsInfo> result = new AnalysisResult();
        try {
            result.appendData(this.referenceData(request));
            Map<String, Object> searchParams = getSearchParams(request);
            List<ActionVisitsInfo> entities = actionAnalysisVisitsService.list(getSearchParams(request));
            ActionVisitsInfo visits = new ActionVisitsInfo(getPeriodLable(searchParams));
            entities.forEach(e -> {
                visits.setPv(visits.getPv() + e.getPv());
                visits.setUv(visits.getUv() + e.getUv());
                visits.setIp(visits.getIp() + e.getIp());
            });
            result.appendData("visitsTotal", visits);
            result.setTotal((long) entities.size());
            result.setRows(entities);
        } catch (Exception var5) {
            this.handleException(result, "列表查询", var5);
        }
        return result;
    }

    private String getPeriodLable(Map<String, Object> searchParams) {
        String start = (String) searchParams.get("GTE_period");
        String end = (String) searchParams.get("LTE_period");
        if (Strings.equals(start, end)) {
            return start;
        } else {
            return start + " ~ " + end;
        }
    }

    @RequestMapping(path = "/widget/visits")
    public String widgetPv(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAllAttributes(referenceData(request));
        return "/manage/alog/analysis/widget/visits";
    }

    @RequestMapping(path = "/widget/visits/cacheClear", method = RequestMethod.POST)
    @ResponseBody
    public AnalysisResult<ActionVisitsInfo> visitsCacheClear(HttpServletRequest request, HttpServletResponse response) {
        AnalysisResult<ActionVisitsInfo> result = new AnalysisResult();
        try {
            String period = Servlets.getParameter(request, "period");
            if (Strings.isBlank(period)) {
                period = Dates.format(new Date(), Dates.CHINESE_DATE_FORMAT_LINE);
            }
            actionAnalysisVisitsService.cacheClear(period);
        } catch (Exception var5) {
            this.handleException(result, "清理实时分析缓存", var5);
        }
        return result;
    }


    @Override
    protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
        model.put("allPeriods", AnalysisPeriod.mapping());
    }

}
