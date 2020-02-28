/*
 * acooly.cn Inc.
 * Copyright (c) 2020 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2020-02-26
 */
package com.acooly.portlets.fqa.core.web;

import com.acooly.core.common.web.AbstractJsonEntityController;
import com.acooly.core.common.web.support.JsonListResult;
import com.acooly.core.utils.Servlets;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.AbleStatus;
import com.acooly.module.treetype.entity.TreeType;
import com.acooly.module.treetype.service.TreeTypeService;
import com.acooly.portlets.fqa.PortletFqaConstants;
import com.acooly.portlets.fqa.core.entity.Fqa;
import com.acooly.portlets.fqa.core.service.FqaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * p_fqa 管理控制器
 *
 * @author zhangpu@acooly.cn
 * @date 2020-02-26 12:31:54
 */
@Controller
@RequestMapping(value = "/manage/portlets/fqa/fqa")
public class FqaManagerController extends AbstractJsonEntityController<Fqa, FqaService> {


    {
        allowMapping = "*";
    }

    @SuppressWarnings("unused")
    @Autowired
    private FqaService fqaService;

    @Autowired
    private TreeTypeService treeTypeService;

    @RequestMapping("loadTypes")
    @ResponseBody
    public JsonListResult<TreeType> loadTypes(HttpServletRequest request, HttpServletResponse response) {
        JsonListResult<TreeType> result = new JsonListResult();
        try {
            result.appendData(this.referenceData(request));
            List<TreeType> treeTypes = treeTypeService.tree(PortletFqaConstants.TREE_TYPE_SCHEME_FQA, "/");
            result.setTotal(Long.valueOf(treeTypes.size()));
            result.setRows(treeTypes);
        } catch (Exception e) {
            this.handleException(result, "加载FQA分类树", e);
        }
        return result;
    }

    @Override
    protected Fqa onSave(HttpServletRequest request, HttpServletResponse response, Model model, Fqa entity, boolean isCreate) throws Exception {
        TreeType treeType = loadTreeType(request);
        if (treeType != null) {
            entity.setAskTypeCode(treeType.getCode());
            entity.setAskTypeName(treeType.getName());
        }
        return entity;
    }

    @Override
    protected void onEdit(HttpServletRequest request, HttpServletResponse response, Model model, Fqa entity) {
        if (entity != null && Strings.isNotBlank(entity.getAskTypeCode())) {
            TreeType treeType = treeTypeService.findByCode(entity.getAskTypeCode());
            model.addAttribute("treeType", treeType);
        }
        super.onEdit(request, response, model, entity);
    }

    @Override
    protected Map<String, Object> getSearchParams(HttpServletRequest request) {
        Map<String, Object> map = super.getSearchParams(request);
        TreeType treeType = loadTreeType(request);
        if (treeType != null) {
            map.put("LIKE_askTypeCode", treeType.getCode());
        }
        return map;
    }

    @Override
    protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
        model.put("allStatuss", AbleStatus.mapping());
    }

    protected TreeType loadTreeType(HttpServletRequest request) {
        Long askTypeId = Servlets.getLongParameter(request, "askTypeId");
        return treeTypeService.get(askTypeId);
    }

}
