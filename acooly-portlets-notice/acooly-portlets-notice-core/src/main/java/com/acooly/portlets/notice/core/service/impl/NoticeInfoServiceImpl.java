/*
 * acooly.cn Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by acooly
 * date:2017-11-23
 */
package com.acooly.portlets.notice.core.service.impl;

import com.acooly.portlets.notice.core.dao.NoticeReadDao;
import com.acooly.portlets.notice.core.entity.NoticeRead;
import com.acooly.portlets.notice.facade.enums.NoticeStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.portlets.notice.core.service.NoticeInfoService;
import com.acooly.portlets.notice.core.dao.NoticeInfoDao;
import com.acooly.portlets.notice.core.entity.NoticeInfo;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 公告消息 Service实现
 * <p>
 * Date: 2017-11-23 00:53:06
 *
 * @author acooly
 */
@Service("noticeInfoService")
@Slf4j
public class NoticeInfoServiceImpl extends EntityServiceImpl<NoticeInfo, NoticeInfoDao> implements NoticeInfoService {

    @Autowired
    private NoticeReadDao noticeReadDao;

    @Override
    @Transactional
    public void noticePushSuccess(String pushNo) {
        getEntityDao().updateStatusByPushNo(NoticeStatusEnum.finish, pushNo);
    }

    @Override
    public long countUnreadByGroup(String receiver, String customGroup) {
        long unreadCounts,allUnreads;

        if (StringUtils.isNotBlank(customGroup)) {
            allUnreads = getEntityDao().countUnreadByGroup(receiver, customGroup);
        } else {
            allUnreads = getEntityDao().countUnread(receiver);
        }

        long readedBroadcastCount = 0;
        NoticeRead readedBroadcast = noticeReadDao.findByReceiver(receiver);
        if (null != readedBroadcast && StringUtils.isNotBlank(readedBroadcast.getBroadcastRead())) {
            readedBroadcastCount = StringUtils.countMatches(readedBroadcast.getBroadcastRead(), ",") + 1;
        }

        unreadCounts = allUnreads - readedBroadcastCount;

        if (unreadCounts < 0) {
            log.error("客户:{}未读消息计算发生异常，计算出的未读消息条数为:{}小于0,可能是脏数据造成.全部未读消息:{},已读广播条数:{}",
                    readedBroadcast, unreadCounts, allUnreads, readedBroadcastCount);
            return unreadCounts;
        } else {
            return unreadCounts;
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void readAll(String receiver, String customGroup) {
        if (StringUtils.isNotBlank(customGroup)) {
            getEntityDao().updateAllNoticeReadedByGroup(receiver, customGroup);
        } else {
            getEntityDao().updateAllNoticeReaded(receiver);
        }
    }

    @Override
    public List<Long> getAllBroadcatIds(String receiver, String customGroup) {
        if (StringUtils.isNotBlank(customGroup)) {
            return getEntityDao().getAllBroadcatIdsByGroup(customGroup);
        } else {
            return getEntityDao().getAllBroadcatIds();
        }
    }

    @Override
    public NoticeInfo findByPushNoAndReceiver(String pushNo, String receiver) {
        return getEntityDao().findByPushNoAndReceiver(pushNo,receiver);
    }
}
