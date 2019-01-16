/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice.core;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.utils.Collections3;
import com.acooly.core.utils.enums.AbleStatus;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.portlets.notice.core.dto.NoticeComponentConstants;
import com.acooly.portlets.notice.core.entity.NoticeInfo;
import com.acooly.portlets.notice.core.entity.NoticeRead;
import com.acooly.portlets.notice.core.service.NoticeInfoService;
import com.acooly.portlets.notice.core.service.NoticeReadService;
import com.acooly.portlets.notice.facade.dto.PageableNoticeInfo;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liubin@prosysoft.com on 2017/12/5.
 */
@Service
@Slf4j
public class NoticeQueryServiceImpl implements NoticeQueryService {

    @Autowired
    private NoticeInfoService noticeInfoService;

    @Autowired
    private NoticeReadService noticeReadService;

    @Override
    public PageInfo<PageableNoticeInfo> pageQuery(String receiver, PageInfo<NoticeInfo> pager,
                                                  Map<String, Object> params,
                                                  Map<String, Boolean> orderBy) {
        Assert.hasText(receiver, "消息接收人不能为空");

        // 获取广播已读ID
        NoticeRead noticeRead = noticeReadService.findByReceiver(receiver);
        if (noticeRead == null) {
            noticeRead = new NoticeRead();
            noticeRead.setStatus(AbleStatus.enable);
            noticeRead.setReceiver(receiver);
            noticeReadService.save(noticeRead);
        }
        List<String> readList = Lists.newArrayList();
        if (StringUtils.isNoneBlank(noticeRead.getBroadcastRead())) {
            readList = Lists.newArrayList(noticeRead.getBroadcastRead().split(","));
        }

        if (orderBy == null || orderBy.isEmpty()) {
            orderBy = new HashMap<>();
            orderBy.put("createTime", false);
        }
        params.remove("EQ_receiver");
        params.put("IN_receiver", new String[]{receiver, NoticeComponentConstants.BROADCAST_RECEIVER});

        // 如果需要查询未读，则特殊处理
        if (params.get("EQ_readed") != null && params.get("EQ_readed").equals(false) && Collections3.isNotEmpty(readList)) {
            params.put("NOTIN_id", readList);
        }


        PageInfo<NoticeInfo> pageResult = noticeInfoService.query(pager, params, orderBy);


        PageInfo<PageableNoticeInfo> pageInfo = new PageInfo<>();
        BeanCopier.copy(pageResult, pageInfo, "pageResults");
        pageInfo.setPageResults(Lists.newArrayList());

        for (NoticeInfo noticeInfo : pageResult.getPageResults()) {
            PageableNoticeInfo pageableNoticeInfo = new PageableNoticeInfo();
            BeanCopier.copy(noticeInfo, pageableNoticeInfo);
            pageableNoticeInfo.setSendTime(DateFormatUtils.format(noticeInfo.getSendTime(), "yyyy-MM-dd HH:mm:ss"));
            if (readList.contains(pageableNoticeInfo.getId().toString())) {
                pageableNoticeInfo.setReaded(true);
            }
            pageInfo.getPageResults().add(pageableNoticeInfo);
        }

        return pageInfo;
    }

    @Override
    public NoticeInfo readNotice(String receiver, Long id) {
        NoticeInfo notice = noticeInfoService.get(id);
        if (notice != null) {
            if (NoticeComponentConstants.BROADCAST_RECEIVER.equals(notice.getReceiver())) {
                noticeReadService.readBroadcast(receiver, id);
            } else {
                if (StringUtils.isNotBlank(receiver) && receiver.equals(notice.getReceiver())) {
                    notice.setReaded(true);
                    noticeInfoService.update(notice);
                } else {
                    throw new BusinessException("消息接收人不匹配", false);
                }
            }
        }

        return notice;
    }

    @Override
    public NoticeInfo readNotice(String pushNo, String receiver) {
        NoticeInfo notice = noticeInfoService.findByPushNoAndReceiver(pushNo, receiver);
        if (notice != null) {
            if (NoticeComponentConstants.BROADCAST_RECEIVER.equals(notice.getReceiver())) {
                noticeReadService.readBroadcast(receiver, notice.getId());
            } else {
                notice.setReaded(true);
                noticeInfoService.update(notice);
            }
        }
        return notice;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW, rollbackFor = Exception.class)
    public void readAll(String receiver, String customGroup) {
        noticeInfoService.readAll(receiver, customGroup);
        List<Long> ids = noticeInfoService.getAllBroadcatIds(receiver, customGroup);
        noticeReadService.read(receiver, ids);
    }

    @Override
    public long countUnreadNotice(String receiver, String customGroup) {
        return noticeInfoService.countUnreadByGroup(receiver, customGroup);
    }

}
