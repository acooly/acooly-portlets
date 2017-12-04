/*
 *
 * www.cutebear.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice.core;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.portlets.notice.core.dto.PageableNoticeInfo;
import com.acooly.portlets.notice.core.entity.NoticeInfo;

import java.util.Map;

/**
 * 消息查询
 * Created by liubin@prosysoft.com on 2017/12/5.
 */
public interface NoticeQueryService {
	
	/**
	 * 分页查询消息 默认按照消息的发送时间降序
	 *
	 * @param pager   分页器
	 * @param params  查询参数
	 * @param orderBy 排序
	 *
	 * @return
	 */
	PageInfo<PageableNoticeInfo> pageQuery (String receiver,PageInfo<NoticeInfo> pager,Map<String, Object> params,
	                                        Map<String, Boolean> orderBy);
	
	
	/**
	 * 阅读消息
	 *
	 * @param
	 *
	 * @return
	 */
	NoticeInfo readNotice (String receiver, Long noticeId);
}
