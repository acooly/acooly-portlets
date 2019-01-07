/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by acooly
 * date:2019-01-06
 */
package com.acooly.portlets.comment.core.web;

import com.acooly.core.common.web.AbstractJQueryEntityController;
import com.acooly.core.utils.enums.WhetherStatus;
import com.acooly.portlets.comment.client.enums.CommentStatusEnum;
import com.acooly.portlets.comment.core.entity.Comment;
import com.acooly.portlets.comment.core.manage.CommentManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
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
    protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
        model.put("allStickys", WhetherStatus.mapping());
        model.put("allStatuss", CommentStatusEnum.mapping());
    }

}
