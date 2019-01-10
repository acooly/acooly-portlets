/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by acooly
 * date:2019-01-06
 */
package com.acooly.portlets.comment.core.web;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.core.utils.Dates;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.WhetherStatus;
import com.acooly.portlets.comment.client.enums.CommentStatusEnum;
import com.acooly.portlets.comment.core.entity.Comment;
import com.acooly.portlets.comment.core.manage.CommentManager;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * p_comment 管理控制器
 *
 * @author acooly
 * Date: 2019-01-06 19:37:26
 */
@Controller
@RequestMapping(value = "/manage/portlets/comment/comment")
public class CommentManagerController extends AbstractJQueryEntityController<Comment, CommentManager> {


    {
        allowMapping = "*";
    }

    @SuppressWarnings("unused")
    @Autowired
    private CommentManager commentManager;


    @Override
    protected Comment doSave(HttpServletRequest request, HttpServletResponse response, Model model, boolean isCreate) throws Exception {
        if (isCreate) {
            throw new UnsupportedOperationException();
        }
        Comment entity = loadEntity(request);
        String status = request.getParameter("status");
        String content = request.getParameter("content");
        if (Strings.equalsIgnoreCase(entity.getStatus().code(), status)) {
            entity.setStatus(CommentStatusEnum.enable_review);
        }
        String originContent = Dates.format(new Date()) + ": " + entity.getContent() + "<br/>" + Strings.trimToEmpty(entity.getOriginContent());
        originContent = Strings.substring(originContent, 0, 1024);
        entity.setOriginContent(originContent);
        entity.setContent(content);
        getEntityService().update(entity);
        return entity;
    }

    @Override
    protected List<String> getExportTitles() {
        return Lists.newArrayList("Id","父Id","业务类型","业务标志","用户标志","用户名称","用户IP","内容","时间","状态");
    }

    @Override
    protected List<String> doExportEntity(Comment entity) {
        List<String> data = Lists.newArrayList();
        data.add(String.valueOf(entity.getId()));
        data.add(String.valueOf(entity.getParentId()));
        data.add(entity.getBusiType());
        data.add(entity.getBusiKey());
        data.add(entity.getUserNo());
        data.add(entity.getUserName());
        data.add(entity.getUserIp());
        data.add(entity.getContent());
        data.add(Dates.format(entity.getCreateTime()));
        data.add(entity.getStatus().message());
        return data;
    }

    @Override
    protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
        model.put("allStickys", WhetherStatus.mapping());
        model.put("allStatuss", CommentStatusEnum.mapping());
    }
}
