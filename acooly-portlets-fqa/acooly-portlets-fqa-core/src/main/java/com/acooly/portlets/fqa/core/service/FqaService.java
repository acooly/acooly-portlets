/*
 * acooly.cn Inc.
 * Copyright (c) 2020 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2020-02-26
 *
 */
package com.acooly.portlets.fqa.core.service;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.service.EntityService;
import com.acooly.portlets.fqa.core.entity.Fqa;
import com.acooly.portlets.fqa.dto.FqaApplyInfo;
import com.acooly.portlets.fqa.dto.FqaInfo;

import java.util.Map;

/**
 * p_fqa Service接口
 *
 * @author zhangpu@acooly.cn
 * @date 2020-02-26 12:31:54
 */
public interface FqaService extends EntityService<Fqa> {

    /**
     * 提交FQA
     *
     * @param fqaApplyInfo
     * @return
     */
    FqaInfo apply(FqaApplyInfo fqaApplyInfo);

    /**
     * 分页查询
     *
     * @param pageInfo
     * @param map
     * @param sortMap
     * @return
     */
    PageInfo<FqaInfo> list(PageInfo<FqaInfo> pageInfo, Map<String, Object> map, Map<String, Boolean> sortMap);

    /**
     * 详情
     *
     * @param id
     * @return
     */
    FqaInfo detail(Long id);

}
