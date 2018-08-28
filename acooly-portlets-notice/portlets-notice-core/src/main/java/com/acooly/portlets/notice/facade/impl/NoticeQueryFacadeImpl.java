/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice.facade.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.facade.PageOrder;
import com.acooly.core.common.facade.PageResult;
import com.acooly.core.common.facade.SingleOrder;
import com.acooly.core.common.facade.SingleResult;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.module.appservice.AppService;
import com.acooly.portlets.notice.core.NoticeQueryService;
import com.acooly.portlets.notice.core.entity.NoticeInfo;
import com.acooly.portlets.notice.facade.NoticeQueryFacade;
import com.acooly.portlets.notice.facade.dto.CountNoticeDto;
import com.acooly.portlets.notice.facade.dto.NoticeDto;
import com.acooly.portlets.notice.facade.dto.NoticeReadDto;
import com.acooly.portlets.notice.facade.dto.PageableNoticeInfo;
import com.acooly.portlets.notice.facade.enums.NoticeContentTypeEnum;
import com.acooly.portlets.notice.facade.enums.NoticeStatusEnum;
import com.alibaba.dubbo.config.annotation.Service;
import com.google.common.io.CountingOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

/**
 * Created by liubin@prosysoft.com on 2017/12/17.
 */
@Service(version = "1.0")
public class NoticeQueryFacadeImpl implements NoticeQueryFacade {
	
	@Autowired
	private NoticeQueryService noticeQueryService;
	
	@Override
	@AppService
	public SingleResult<NoticeDto> readNotice (SingleOrder<NoticeReadDto> readOrder) {
		SingleResult<NoticeDto> result = new SingleResult<> ();
		NoticeInfo noticeInfo = noticeQueryService
				.readNotice (readOrder.getDto ().getReceiver (), readOrder.getDto ().getNoticeId ());
		NoticeDto noticeDto = new NoticeDto ();
		BeanCopier.copy (noticeInfo, noticeDto);
		
		
		result.setDto (noticeDto);
		return result;
	}
	
	@Override
	@AppService
	public PageResult<PageableNoticeInfo> pageList (PageOrder order) {
		
		PageResult<PageableNoticeInfo> pageResult = new PageResult<> ();
		
		String receiver = (String) order.getMap ().get ("EQ_receiver");
		Assert.hasText (receiver, "消息接收人不能为空");
		PageInfo<PageableNoticeInfo> pageInfo = noticeQueryService
				.pageQuery (receiver, order.getPageInfo (), order.getMap (), order.getSortMap ());
		
		pageResult.setDto (pageInfo);
		
		return pageResult;
	}

    @Override
    @AppService
    public SingleResult<Long> countUnreadNotice(SingleOrder<CountNoticeDto> order) {
	    long count = noticeQueryService.countUnreadNotice(order.getDto().getReceiver(),order.getDto().getCustomGroup());
	    SingleResult<Long> result = new SingleResult<>();
	    result.setDto(count);
        return result;
    }
}
