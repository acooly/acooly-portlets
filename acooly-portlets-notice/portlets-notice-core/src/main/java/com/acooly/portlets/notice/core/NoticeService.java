/*
 * www.acooly.cn Inc.
 * Copyright (c) 2017 All Rights Reserved
 */

/*
 * 修订记录:
 * zhangpu@acooly.cn 2017-11-23 01:02 创建
 */
package com.acooly.portlets.notice.core;



import com.acooly.portlets.notice.core.dto.NoticeMessage;

import java.util.List;

/**
 * @author zhangpu 2017-11-23 01:02
 */
public interface NoticeService {

    /**
     * 群发
     *
     * @param noticeMessage
     * @param targets
     */
    void group(NoticeMessage noticeMessage, List<String> targets);

    /**
     * 广播
     *
     * @param noticeMessage
     */
    void broadcast(NoticeMessage noticeMessage);

}
