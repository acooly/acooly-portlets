/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-03-23
 */
package com.acooly.portlets.alog.analysis.persist.dao;

import com.acooly.module.mybatis.EntityMybatisDao;
import com.acooly.portlets.alog.analysis.persist.entity.ActionAnalysisVisits;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 访问量统计 Mybatis Dao
 * <p>
 * Date: 2019-03-23 16:38:18
 *
 * @author zhangpu@acooly.cn
 */
public interface ActionAnalysisVisitsDao extends EntityMybatisDao<ActionAnalysisVisits> {

    @Select("select * from p_action_analysis_visits where period = #{period}")
    List<ActionAnalysisVisits> findByActionDayAndType(@Param("period") String period);

}
