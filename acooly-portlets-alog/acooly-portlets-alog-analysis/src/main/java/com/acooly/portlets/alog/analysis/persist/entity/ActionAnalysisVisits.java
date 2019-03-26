/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-03-23
 */
package com.acooly.portlets.alog.analysis.persist.entity;


import com.acooly.core.common.domain.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 访问量统计 Entity
 *
 * @author zhangpu@acooly.cn
 * Date: 2019-03-23 23:33:33
 */
@Entity
@Table(name = "p_action_analysis_visits")
@Getter
@Setter
public class ActionAnalysisVisits extends AbstractEntity {

    /**
     * 统计日期
     */
    @NotNull
    private String period;

    private int pv = 0;

    private int uv = 0;

    private int ip = 0;

    public ActionAnalysisVisits() {
    }

    public ActionAnalysisVisits(String period, int pv, int uv, int ip) {
        this.period = period;
        this.pv = pv;
        this.uv = uv;
        this.ip = ip;
    }

}
