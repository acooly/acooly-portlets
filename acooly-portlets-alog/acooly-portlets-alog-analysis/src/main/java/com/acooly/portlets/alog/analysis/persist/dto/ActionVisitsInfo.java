/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-24 17:57
 */
package com.acooly.portlets.alog.analysis.persist.dto;

import com.acooly.core.common.facade.InfoBase;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

/**
 * @author zhangpu
 * @date 2019-03-24 17:57
 */
@Getter
@Setter
public class ActionVisitsInfo extends InfoBase {

    @NotNull
    private String period;

    private int pv = 0;

    private int uv = 0;

    private int ip = 0;

    public ActionVisitsInfo() {
    }

    public ActionVisitsInfo(String period) {
        this.period = period;
    }

    public ActionVisitsInfo(String period, int pv, int uv, int ip) {
        this.period = period;
        this.pv = pv;
        this.uv = uv;
        this.ip = ip;
    }
}
