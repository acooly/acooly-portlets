/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-23 17:33
 */
package com.acooly.portlets.alog.analysis.persist.dao.impl;

import com.acooly.core.utils.Dates;
import com.acooly.core.utils.Strings;
import com.acooly.module.ds.AbstractJdbcTemplateDao;
import com.acooly.portlets.alog.analysis.persist.dao.ActionVisitsDao;
import com.acooly.portlets.alog.analysis.persist.dto.ActionVisitsInfo;
import com.acooly.portlets.alog.analysis.persist.enums.AnalysisPeriod;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author zhangpu
 * @date 2019-03-23 17:33
 */
@Slf4j
@Repository
public class ActionVisitsDaoJdbcImpl extends AbstractJdbcTemplateDao implements ActionVisitsDao {


    @Override
    public List<ActionVisitsInfo> list(Map<String, Object> map, Map<String, Boolean> orderMap) {
        AnalysisPeriod analysisPeriod = AnalysisPeriod.find((String) map.get("EQ_analysisPeriod"));
        String startPeriod = (String) map.get("GTE_period");
        String endPeriod = (String) map.get("LTE_period");
        if (analysisPeriod == null || Strings.isBlank(startPeriod) || Strings.isBlank(endPeriod)) {
            return Lists.newArrayList();
        }

        String sql = null;
        if (analysisPeriod == AnalysisPeriod.DAY) {
            sql = "select period,pv,uv,ip from p_action_analysis_visits where period >= '" + startPeriod + "' and period <= '" + endPeriod + "'";
        } else if (analysisPeriod == AnalysisPeriod.WEEK) {
            sql = "select DATE_FORMAT(period,'%Y-第%U周') as weeks ,sum(pv) as pv,sum(uv) as uv,sum(ip) as ip " +
                    "from p_action_analysis_visits " +
                    "where period >= '" + startPeriod + "' and period < '" + endPeriod + "' " +
                    "GROUP BY weeks";
        } else if (analysisPeriod == AnalysisPeriod.MONTH) {
            sql = "select CONCAT(YEAR(period),'-',DATE_FORMAT(period,'%m')) months,sum(pv) as pv,sum(uv) as uv,sum(ip) as ip " +
                    "from p_action_analysis_visits " +
                    "where period >= '" + startPeriod + "' and period < '" + endPeriod + "' " +
                    "GROUP BY months";
        } else if (analysisPeriod == AnalysisPeriod.QUARTER) {
            sql = "select CONCAT(YEAR(period),'-Q',QUARTER(period)) as quarter,sum(pv) as pv,sum(uv) as uv,sum(ip) as ip " +
                    "from p_action_analysis_visits " +
                    "where period >= '" + startPeriod + "' and period < '" + endPeriod + "' " +
                    "GROUP BY quarter";
        } else {
            return Lists.newArrayList();
        }

        List<ActionVisitsInfo> actionVisitsInfos = jdbcTemplate.query(sql, new RowMapper<ActionVisitsInfo>() {
            @Override
            public ActionVisitsInfo mapRow(ResultSet rs, int i) throws SQLException {
                return new ActionVisitsInfo(rs.getString(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        rs.getInt(4));
            }
        });

        return actionVisitsInfos;
    }

    @Override
    public int pv(Date date) {
        String sql = "select count(*) from p_action_log where action_time >= ? and action_time < ?";
        return doCountSql(sql, date);
    }

    @Override
    public int uv(Date date) {
        String sql = "select count(*) from (select distinct cookies from p_action_log where action_time >= ? and action_time < ?) t1";
        return doCountSql(sql, date);
    }

    @Override
    public int ip(Date date) {
        String sql = "select count(*) from (select distinct user_ip from p_action_log where action_time >= ? and action_time < ?) t1";
        return doCountSql(sql, date);
    }

    protected int doCountSql(String sql, Date date) {
        String startDay = Dates.format(date, Dates.CHINESE_DATE_FORMAT_LINE);
        Date start = Dates.parse(startDay);
        Object[] args = {start, Dates.addDay(start)};
        int count = jdbcTemplate.queryForObject(sql, args, Integer.class);
        log.debug("execute-count day:{}, count:{}", startDay, count);
        return count;
    }

}
