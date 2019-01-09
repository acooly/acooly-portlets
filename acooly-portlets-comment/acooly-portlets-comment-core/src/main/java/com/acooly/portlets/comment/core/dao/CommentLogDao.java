/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by acooly
 * date:2019-01-09
 */
package com.acooly.portlets.comment.core.dao;

import com.acooly.module.mybatis.EntityMybatisDao;
import com.acooly.portlets.comment.client.enums.CommentLogActionTypeEnum;
import com.acooly.portlets.comment.core.entity.CommentLog;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 评论日志 Mybatis Dao
 * <p>
 * Date: 2019-01-09 00:46:41
 *
 * @author acooly
 */
public interface CommentLogDao extends EntityMybatisDao<CommentLog> {

    /**
     * 根据评论ID+userNo+actionType查询 --> 判断是否有点赞
     *
     * @param commentId
     * @param userNo
     * @param actionType
     * @return
     */
    @Select("select * from p_comment_log where comment_id = #{commentId} and user_no = #{userNo} and action_type = #{actionType} limit 0,1")
    CommentLog findByCommentIdAndUserNoAndActionTypeForTop(@Param("commentId") Long commentId,
                                                           @Param("userNo") String userNo,
                                                           @Param("actionType") CommentLogActionTypeEnum actionType);


}
