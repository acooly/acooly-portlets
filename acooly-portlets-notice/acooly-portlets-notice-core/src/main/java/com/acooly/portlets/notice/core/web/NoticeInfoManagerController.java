/*
 * acooly.cn Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by acooly
 * date:2017-11-23
 */
package com.acooly.portlets.notice.core.web;

import com.acooly.core.common.web.AbstractJsonEntityController;
import com.acooly.core.common.web.support.JsonResult;
import com.acooly.core.utils.mapper.JsonMapper;
import com.acooly.portlets.notice.PortletNoticeProperties;
import com.acooly.portlets.notice.core.NoticeService;
import com.acooly.portlets.notice.core.dto.NoticeMessage;
import com.acooly.portlets.notice.core.entity.NoticeInfo;
import com.acooly.portlets.notice.core.service.MessageGroupLoader;
import com.acooly.portlets.notice.core.service.NoticeInfoService;
import com.acooly.portlets.notice.facade.enums.DeviceTypeEnum;
import com.acooly.portlets.notice.facade.enums.NoticeContentTypeEnum;
import com.acooly.portlets.notice.facade.enums.NoticeStatusEnum;
import com.acooly.portlets.notice.facade.enums.NoticeTypeEnum;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 公告消息 管理控制器
 *
 * @author acooly
 * Date: 2017-11-23 00:53:05
 */
@Controller
@RequestMapping(value = "/manage/portlets/notice/noticeInfo")
public class NoticeInfoManagerController extends AbstractJsonEntityController<NoticeInfo, NoticeInfoService> {

    {
        allowMapping = "*";
    }

    @SuppressWarnings("unused")
    @Autowired
    private NoticeInfoService noticeInfoService;

    @Autowired
    private NoticeService noticeService;

    @Autowired
    private PortletNoticeProperties portletNoticeProperties;

    @Autowired
    private MessageGroupLoader messageGroupLoader;

    @Override
    protected void referenceData(HttpServletRequest request, Map<String, Object> model) {
        model.put("allContentTypes", NoticeContentTypeEnum.mapping());
        model.put("allStatuss", NoticeStatusEnum.mapping());
        model.put("allTypes", NoticeTypeEnum.mapping());
        model.put("allDeviceTypes", DeviceTypeEnum.mapping());
        model.put("pushProvider", portletNoticeProperties.getPushProvider().code());
        Map<String, String> messageGroups = messageGroupLoader.loadMessageGroups();
        if (messageGroups != null && messageGroups.size() > 0) {
            model.put("messageGroups", messageGroups);
        }

    }


    @RequestMapping("push")
    @ResponseBody
    public JsonResult push(NoticeInfo noticeInfo, DeviceTypeEnum deviceType) {
        JsonResult jsonResult = new JsonResult();
        try {

            Assert.notNull(noticeInfo.getType(), "发送类型不能为空");
            NoticeMessage noticeMessage = new NoticeMessage();
            noticeMessage.setDeviceType(deviceType);
            if (StringUtils.isNotBlank(noticeInfo.getContext())) {
                Map<String, Object> context = JsonMapper.nonEmptyMapper()
                        .fromJson(noticeInfo.getContext(), Map.class);
                noticeMessage.setContext(context);
            }

            noticeMessage.setPush(true);
            noticeMessage.setContent(noticeInfo.getContent());
            noticeMessage.setTitle(noticeInfo.getTitle());
            noticeMessage.setCustomGroup(noticeInfo.getCustomGroup());

            List<String> targets = null;
            if (StringUtils.isNotBlank(noticeInfo.getReceiver())) {
                targets = Lists.newArrayList(noticeInfo.getReceiver().split(","));
            }

            if (NoticeTypeEnum.broadcast.equals(noticeInfo.getType())) {
                noticeService.broadcast(noticeMessage);
            } else {
                Assert.notEmpty(targets, "选择群发时收信人必须填写");
                noticeService.group(noticeMessage, targets);
            }
            jsonResult.setMessage("消息推送成功");

        } catch (Exception e) {
            handleException(jsonResult, "后台推送消息出错", e);
        }

        return jsonResult;
    }

}
