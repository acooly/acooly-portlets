/*
 * acooly.cn Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by acooly
 * date:2017-11-23
 */
 package com.acooly.portlets.notice.core.dao;

import com.acooly.module.mybatis.EntityMybatisDao;
import com.acooly.portlets.notice.core.entity.NoticeInfo;
import com.acooly.portlets.notice.facade.enums.NoticeStatusEnum;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 公告消息 Mybatis Dao
 *
 * Date: 2017-11-23 00:53:06
 * @author acooly
 */
public interface NoticeInfoDao extends EntityMybatisDao<NoticeInfo> {
 
 @Update ("update p_notice_info set status=#{status} where push_no =#{pushNo} ")
  void updateStatusByPushNo(@Param ("status") NoticeStatusEnum status, @Param ("pushNo") String pushNo);

}
