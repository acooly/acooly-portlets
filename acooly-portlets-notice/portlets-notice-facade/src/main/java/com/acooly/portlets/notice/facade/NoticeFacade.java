/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice.facade;

import com.acooly.core.common.facade.ResultBase;
import com.acooly.core.common.facade.SingleOrder;
import com.acooly.core.common.facade.SingleResult;
import com.acooly.portlets.notice.facade.dto.NoticeMessage;

/**
 * Created by liubin@prosysoft.com on 2017/12/16.
 */
public interface NoticeFacade {
	
	/**
	 * 消息推送
	 * @param pushOrder
	 * @return
	 */
	ResultBase push(SingleOrder<NoticeMessage> pushOrder);
}
