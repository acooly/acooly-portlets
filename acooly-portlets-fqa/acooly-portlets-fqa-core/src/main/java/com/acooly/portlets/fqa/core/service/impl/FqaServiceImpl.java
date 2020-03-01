/*
 * acooly.cn Inc.
 * Copyright (c) 2020 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2020-02-26
 */
package com.acooly.portlets.fqa.core.service.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.AbleStatus;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.module.treetype.entity.TreeType;
import com.acooly.module.treetype.service.TreeTypeService;
import com.acooly.portlets.fqa.core.PortletFqaProperties;
import com.acooly.portlets.fqa.core.dao.FqaDao;
import com.acooly.portlets.fqa.core.entity.Fqa;
import com.acooly.portlets.fqa.core.service.FqaService;
import com.acooly.portlets.fqa.dto.FqaApplyInfo;
import com.acooly.portlets.fqa.dto.FqaInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * p_fqa Service实现
 *
 * @author zhangpu@acooly.cn
 * @date 2020-02-26 12:31:54
 */
@Service("fqaService")
public class FqaServiceImpl extends EntityServiceImpl<Fqa, FqaDao> implements FqaService {

    @Autowired
    private TreeTypeService treeTypeService;

    @Override
    public FqaInfo apply(FqaApplyInfo fqaApplyInfo) {
        Fqa fqa = new Fqa();
        BeanCopier.copy(fqaApplyInfo, fqa);
        fqa.setStatus(AbleStatus.enable);

        if (Strings.isNotBlank(fqa.getAskTypeCode())) {
            TreeType treeType = treeTypeService.findByCode(PortletFqaProperties.TREE_TYPE_SCHEME_FQA,
                    fqa.getAskTypeCode());
            if (treeType != null) {
                fqa.setAskTypeName(treeType.getName());
            }
        }
        save(fqa);
        FqaInfo fqaInfo = convertFqa(fqa);
        return fqaInfo;
    }

    @Override
    public FqaInfo detail(Long id) {

        Fqa fqa = get(id);
        if (fqa == null) {
            throw new BusinessException(CommonErrorCodes.OBJECT_NOT_EXIST);
        }
        return convertFqa(fqa);
    }

    protected FqaInfo convertFqa(Fqa fqa) {
        FqaInfo fqaInfo = new FqaInfo();
        BeanCopier.copy(fqa, fqaInfo);
        return fqaInfo;
    }
}
