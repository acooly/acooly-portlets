/*
 * acooly.cn Inc.
 * Copyright (c) 2021 All Rights Reserved.
 * create by zhangpu
 * date:2021-05-08
 */
package com.acooly.portlets.successtory.core.web;

import com.acooly.core.common.web.AbstractJsonEntityController;
import com.acooly.module.ofile.OFileProperties;
import com.acooly.portlets.successtory.core.PortletSuccesstoryProperties;
import com.acooly.portlets.successtory.core.entity.SuccessStory;
import com.acooly.portlets.successtory.core.service.SuccessStoryService;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * p_success_story 管理控制器
 *
 * @author zhangpu
 * @date 2021-05-08 18:12:01
 */
@Controller
@RequestMapping(value = "/manage/portlets/successtory/successStory")
public class SuccessStoryManagerController extends AbstractJsonEntityController<SuccessStory, SuccessStoryService> {


    {
        allowMapping = "*";
    }

    @SuppressWarnings("unused")
    @Autowired
    private SuccessStoryService successStoryService;

    @Autowired
    private OFileProperties oFileProperties;

    @Override
    protected SuccessStory onSave(HttpServletRequest request, HttpServletResponse response, Model model, SuccessStory entity, boolean isCreate) throws Exception {
        // 设置上传文件的根存储路径
        UploadConfig uploadConfig = getUploadConfig();
        uploadConfig.setStorageRoot(oFileProperties.getStorageRoot());
        uploadConfig.setStorageNameSpace(PortletSuccesstoryProperties.COMPONENT_NAME);
        uploadConfig.setNeedTimePartPath(false);
        uploadConfig.setUseMemery(false);
        // 上传文件，相对路径绑定到对应的属性
        doUpload(request, entity);
        return super.onSave(request, response, model, entity, isCreate);
    }

    @Override
    protected Map<String, Boolean> getSortMap(HttpServletRequest request) {
        Map<String, Boolean> sortMap = Maps.newLinkedHashMap();
        sortMap.put("sortTime", false);
        return sortMap;
    }

    @Override
    protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
        model.put("serverRoot", oFileProperties.getServerRoot());
    }

}
