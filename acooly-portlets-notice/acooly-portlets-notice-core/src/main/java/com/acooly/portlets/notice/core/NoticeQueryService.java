/*
 *
 * www.cutebear.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice.core;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.portlets.notice.core.entity.NoticeInfo;
import com.acooly.portlets.notice.facade.dto.PageableNoticeInfo;

import java.util.Map;

/**
 * 消息查询
 * Created by liubin@prosysoft.com on 2017/12/5.
 *
 * @date 2018-12-31 zhangpu 补充javadoc
 */
public interface NoticeQueryService {

    /**
     * 分页查询消息 默认按照消息的发送时间降序
     *
     * @param pager   分页器
     * @param params  查询参数 acooly实体查询规则（如：EQ_customGroup：表示查询对应自定义分类的消息）
     * @param orderBy 排序
     * @return
     */
    PageInfo<PageableNoticeInfo> pageQuery(String receiver, PageInfo<NoticeInfo> pager, Map<String, Object> params,
                                           Map<String, Boolean> orderBy);


    /**
     * 阅读消息
     *
     * @param
     * @return
     */
    NoticeInfo readNotice(String receiver, Long noticeId);

    /**
     * 阅读消息
     *
     * @param
     * @return
     */
    NoticeInfo readNotice(String pushNo, String receiver);

    /**
     * 全部已读
     *
     * @param receiver
     * @param customGroup 消息组 如果不传递则返回所有消息
     */
    void readAll(String receiver, String customGroup);

    /**
     * 查询未读消息条数
     *
     * @param receiver    接收人
     * @param customGroup 消息组 如果不传递则返回所有消息
     * @return 未读消息数
     */
    long countUnreadNotice(String receiver, String customGroup);
}
