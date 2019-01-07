/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by acooly
 * date:2019-01-06
 */
package com.acooly.portlets.comment.core.dao;

import com.acooly.module.mybatis.EntityMybatisDao;
import com.acooly.portlets.comment.core.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * p_comment Mybatis Dao
 * <p>
 * Date: 2019-01-06 19:37:26
 *
 * @author acooly
 */
public interface CommentDao extends EntityMybatisDao<Comment> {


    @Select("select * from p_comment where id = #{id} for update")
    Comment lockComment(@Param("id") Long id);


}
