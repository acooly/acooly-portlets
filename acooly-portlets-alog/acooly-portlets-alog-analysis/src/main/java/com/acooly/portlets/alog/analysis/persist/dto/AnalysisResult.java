/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-24 18:36
 */
package com.acooly.portlets.alog.analysis.persist.dto;

import com.acooly.core.common.web.support.JsonListResult;
import lombok.Data;

import java.util.List;

/**
 * @author zhangpu
 * @date 2019-03-24 18:36
 */
@Data
public class AnalysisResult<T> extends JsonListResult<T> {

    private List<String> titles;

}
