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
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.RowCallbackHandler;
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


    String sqlForPvByHour = "select DATE_FORMAT(`action_time`,'%H') as period ,count(*) as pv " +
            "from p_action_log where action_time >= ? and action_time < ? " +
            "group by period";

    String sqlForUvByHour = "select t1.period, count(*) from " +
            "(select DATE_FORMAT(action_time,'%H') as period,cookies,count(*) " +
            "from p_action_log where action_time >= ? and action_time < ? and cookies is not null " +
            "group by period,cookies) t1 group by t1.period";

    String sqlForIpByHour = "select t1.period, count(*) from " +
            "(select DATE_FORMAT(action_time,'%H') as period,user_ip,count(*) " +
            "from p_action_log where action_time >= ? and action_time < ? and user_ip is not null " +
            "group by period,user_ip) t1 group by t1.period";


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
    public List<ActionVisitsInfo> listRealTime(Map<String, Object> map, Map<String, Boolean> orderMap) {
        String startPeriod = (String) map.get("GTE_period");
        String endPeriod = (String) map.get("LTE_period");
        if (!Strings.equals(startPeriod, endPeriod)) {
            throw new RuntimeException("开始日期必须等于结束日期，实时查询智能查询一天的数据");
        }

        List<Object> args = Lists.newArrayList();
        Date start = Dates.parse(startPeriod);
        args.add(start);
        if (Strings.equals(startPeriod, endPeriod)) {
            args.add(Dates.addDay(start));
        } else {
            args.add(Dates.parse(endPeriod));
        }

        Map<String, ActionVisitsInfo> resultMap = Maps.newLinkedHashMap();
        String period = null;
        for (int i = 1; i < 24; i++) {
            period = (i < 10 ? "0" + i : String.valueOf(i));
            resultMap.put(period, new ActionVisitsInfo(period));
        }

        // 查询PV
        jdbcTemplate.query(sqlForPvByHour, args.toArray(), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                resultMap.get(rs.getString(1)).setPv(rs.getInt(2));
            }
        });
        // 查询UV
        jdbcTemplate.query(sqlForUvByHour, args.toArray(), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                resultMap.get(rs.getString(1)).setUv(rs.getInt(2));
            }
        });

        // 查询IP
        jdbcTemplate.query(sqlForIpByHour, args.toArray(), new RowCallbackHandler() {
            @Override
            public void processRow(ResultSet rs) throws SQLException {
                resultMap.get(rs.getString(1)).setIp(rs.getInt(2));
            }
        });
        return Lists.newArrayList(resultMap.values());
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
