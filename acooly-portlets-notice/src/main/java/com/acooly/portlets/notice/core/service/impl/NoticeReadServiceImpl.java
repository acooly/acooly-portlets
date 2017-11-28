/*
 * acooly.cn Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by acooly
 * date:2017-11-23
 */
package com.acooly.portlets.notice.core.service.impl;

import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.portlets.notice.core.service.NoticeReadService;
import com.acooly.portlets.notice.core.dao.NoticeReadDao;
import com.acooly.portlets.notice.core.entity.NoticeRead;

/**
 * 公告消息读取状态 Service实现
 *
 * Date: 2017-11-23 00:53:06
 *
 * @author acooly
 *
 */
@Service("noticeReadService")
public class NoticeReadServiceImpl extends EntityServiceImpl<NoticeRead, NoticeReadDao> implements NoticeReadService {

}
