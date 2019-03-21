/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-01-10
 */
package com.acooly.portlets.alog.core.dao;

import com.acooly.module.mybatis.EntityMybatisDao;
import com.acooly.portlets.alog.core.entity.ActionMapping;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 访问映射 Mybatis Dao
 * <p>
 * Date: 2019-01-10 18:10:55
 *
 * @author zhangpu@acooly.cn
 */
public interface ActionMappingDao extends EntityMybatisDao<ActionMapping> {


    @Select("select * from p_action_mapping where action_key = #{actionKey}")
    List<ActionMapping> findByActionKey(@Param("actionKey") String actionKey);


}
