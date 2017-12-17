/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice.facade;

import com.acooly.core.common.facade.PageOrder;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.SingleOrder;
import com.acooly.core.common.facade.SingleResult;
import com.acooly.portlets.notice.facade.dto.NoticeDto;
import com.acooly.portlets.notice.facade.dto.NoticeReadDto;
import com.acooly.portlets.notice.facade.dto.PageableNoticeInfo;

/**
 * Created by liubin@prosysoft.com on 2017/12/16.
 */
public interface NoticeQueryFacade {
	
	/**
	 * 读取消息
	 * @param readOrder
	 * @return
	 */
	SingleResult<NoticeDto> readNotice(SingleOrder<NoticeReadDto> readOrder);
	
	
	/**
	 * 分页查询消息
	 * @param order
	 * @return
	 */
	PageResult<PageableNoticeInfo> pageList(PageOrder order);
}
