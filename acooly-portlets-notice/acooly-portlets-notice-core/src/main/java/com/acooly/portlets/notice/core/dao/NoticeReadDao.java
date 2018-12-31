/*
 * acooly.cn Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by acooly
 * date:2017-11-23
 */
 package com.acooly.portlets.notice.core.dao;

import com.acooly.module.mybatis.EntityMybatisDao;
import com.acooly.portlets.notice.core.entity.NoticeRead;
import org.apache.ibatis.annotations.Select;

/**
 * 公告消息读取状态 Mybatis Dao
 *
 * Date: 2017-11-23 00:53:06
 * @author acooly
 */
public interface NoticeReadDao extends EntityMybatisDao<NoticeRead> {

    @Select("select * from p_notice_read where receiver=#{receiver} and status='enable'")
    NoticeRead findByReceiver(String receiver);

}
