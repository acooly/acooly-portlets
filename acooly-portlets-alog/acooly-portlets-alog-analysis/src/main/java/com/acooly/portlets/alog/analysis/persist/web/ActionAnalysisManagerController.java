/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-03-23
 */
package com.acooly.portlets.alog.analysis.persist.web;

import com.acooly.core.common.web.AbstractJQueryEntityController;
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
public class ActionAnalysisManagerController extends AbstractJQueryEntityController<ActionAnalysisVisits, ActionAnalysisVisitsService> {


    {
        allowMapping = "";
    }

    @SuppressWarnings("unused")
    @Autowired
    private ActionAnalysisVisitsService actionAnalysisVisitsService;

    @RequestMapping(path = "visits", method = RequestMethod.GET)
    public String visitsView(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAllAttributes(referenceData(request));
        return "/manage/alog/analysis/visits";
    }


    @RequestMapping(path = "visits", method = RequestMethod.POST)
    @ResponseBody
    public AnalysisResult<ActionVisitsInfo> visitsQuery(HttpServletRequest request, HttpServletResponse response) {
        AnalysisResult<ActionVisitsInfo> result = new AnalysisResult();
        try {
            result.appendData(this.referenceData(request));
            List<ActionVisitsInfo> entities = actionAnalysisVisitsService.list(getSearchParams(request));
            result.setTotal((long) entities.size());
            result.setRows(entities);
        } catch (Exception var5) {
            this.handleException(result, "列表查询", var5);
        }
        return result;
    }

    @Override
    protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
        model.put("allPeriods", AnalysisPeriod.mapping());
    }

}
