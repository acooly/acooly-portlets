/*
 * acooly.cn Inc.
 * Copyright (c) 2017 All Rights Reserved.
 * create by acooly
 * date:2017-11-23
 */
 package com.acooly.portlets.notice.core.dao;
 
 import  java.util.List;

import com.acooly.module.mybatis.EntityMybatisDao;
import com.acooly.portlets.notice.core.entity.NoticeInfo;
import com.acooly.portlets.notice.facade.enums.NoticeStatusEnum;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
 
 
 @Select ("select count(id) from p_notice_info where (receiver='ALL' or receiver=#{receiver}) and custom_group = #{customGroup} and readed=false")
 long countUnreadByGroup(@Param("receiver") String receiver,@Param ("customGroup") String customGroup);

 @Select ("select count(id) from p_notice_info where (receiver='ALL' or receiver=#{receiver}) and readed=false")
 long countUnread(@Param("receiver") String receiver);
 
 @Update ("update p_notice_info set readed = true where receiver=#{receiver} and custom_group = #{customGroup}")
 void updateAllNoticeReadedByGroup (@Param ("receiver") String receiver, @Param ("customGroup") String customGroup);
 
 @Update ("update p_notice_info set readed = true where receiver=#{receiver}")
 void updateAllNoticeReaded (@Param ("receiver") String receiver);
 
 @Select ("select id from p_notice_info where receiver='ALL' and custom_group = #{customGroup}")
 List<Long> getAllBroadcatIdsByGroup (@Param ("customGroup") String customGroup);
 
 @Select ("select id from p_notice_info where receiver='ALL'")
 List<Long> getAllBroadcatIds();

 @Select("select * from p_notice_info where push_no=#{pushNo} and (receiver = #{recevier} or receiver = 'ALL')")
 NoticeInfo findByPushNoAndReceiver(@Param("pushNo") String pushNo,@Param("receiver") String receiver);
}
