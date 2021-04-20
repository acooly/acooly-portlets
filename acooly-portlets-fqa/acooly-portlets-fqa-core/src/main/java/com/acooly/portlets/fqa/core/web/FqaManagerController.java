/*
 * acooly.cn Inc.
 * Copyright (c) 2020 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2020-02-26
 */
package com.acooly.portlets.fqa.core.web;

import com.acooly.core.common.web.AbstractJsonEntityController;
import com.acooly.core.common.web.MappingMethod;
import com.acooly.core.common.web.support.JsonEntityResult;
import com.acooly.core.utils.Servlets;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.AbleStatus;
import com.acooly.module.treetype.entity.TreeType;
import com.acooly.module.treetype.service.TreeTypeService;
import com.acooly.portlets.fqa.core.PortletFqaProperties;
import com.acooly.portlets.fqa.core.entity.Fqa;
import com.acooly.portlets.fqa.core.service.FqaService;
import com.acooly.portlets.fqa.dto.FqaApplyInfo;
import com.acooly.portlets.fqa.dto.FqaInfo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @Override
    public JsonEntityResult<Fqa> saveJson(HttpServletRequest request, HttpServletResponse response) {
        JsonEntityResult<Fqa> result = new JsonEntityResult();
        this.allow(request, response, MappingMethod.create);
        try {
            FqaApplyInfo applyInfo = new FqaApplyInfo();
            bindNotValidator(request, applyInfo);
            setTreeType(request, applyInfo);
            Fqa fqa = getEntityService().save(applyInfo);
            result.setEntity(fqa);
            result.setMessage("新增成功");
        } catch (Exception e) {
            this.handleException(result, "新增", e);
        }
        return result;
    }

    @Override
    public JsonEntityResult<Fqa> updateJson(HttpServletRequest request, HttpServletResponse response) {
        this.allow(request, response, MappingMethod.update);
        JsonEntityResult result = new JsonEntityResult();
        try {
            Fqa entity = this.loadEntity(request);
            FqaApplyInfo applyInfo = new FqaApplyInfo();
            bindNotValidator(request, applyInfo);
            setTreeType(request, applyInfo);
            entity = getEntityService().update(applyInfo);
            result.setEntity(entity);
            result.setMessage("更新成功");
        } catch (Exception var5) {
            this.handleException(result, "更新", var5);
        }
        return result;
    }

    @Override
    public String edit(HttpServletRequest request, HttpServletResponse response, Model model) {
        return super.edit(request, response, model);
    }

    @Override
    protected void onEdit(HttpServletRequest request, HttpServletResponse response, Model model, Fqa entity) {
        if (entity != null && Strings.isNotBlank(entity.getTypeCode())) {
            TreeType treeType = treeTypeService.findByCode(PortletFqaProperties.TREE_TYPE_SCHEME_FQA,
                    entity.getTypeCode());
            model.addAttribute("treeType", treeType);
        }
        FqaInfo fqaInfo = getEntityService().detail(entity.getId());
        entity.setAnswer(fqaInfo.getAnswer());
        super.onEdit(request, response, model, entity);
    }

    @Override
    protected void onShow(HttpServletRequest request, HttpServletResponse response, Model model, Fqa entity)
            throws Exception {
        FqaInfo fqaInfo = getEntityService().detail(entity.getId());
        entity.setAnswer(fqaInfo.getAnswer());
        super.onShow(request, response, model, entity);
    }

    @Override
    protected Map<String, Object> getSearchParams(HttpServletRequest request) {
        Map<String, Object> map = super.getSearchParams(request);
        TreeType treeType = loadTreeType(request);
        if (treeType != null) {
            map.put("LIKE_typeCode", treeType.getCode());
        }
        return map;
    }

    @Override
    protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
        model.put("allStatuss", AbleStatus.mapping());
        model.put("user", SecurityUtils.getSubject().getPrincipal());
    }

    protected TreeType loadTreeType(HttpServletRequest request) {
        String askTypeId = Servlets.getParameter(request, "askTypeId");
        if (Strings.isNumeric(askTypeId)) {
            return treeTypeService.get(Long.valueOf(askTypeId));
        }
        return null;
    }

    protected void setTreeType(HttpServletRequest request, FqaApplyInfo fqaApplyInfo) {
        TreeType treeType = loadTreeType(request);
        if (treeType != null) {
            fqaApplyInfo.setTypeCode(treeType.getCode());
        }
    }

}
