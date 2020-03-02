/*
 * acooly.cn Inc.
 * Copyright (c) 2020 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2020-02-26
 */
package com.acooly.portlets.fqa.core.service.impl;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Asserts;
import com.acooly.core.utils.Strings;
import com.acooly.core.utils.enums.AbleStatus;
import com.acooly.core.utils.mapper.BeanCopier;
import com.acooly.module.treetype.entity.TreeType;
import com.acooly.module.treetype.service.TreeTypeService;
import com.acooly.portlets.fqa.core.PortletFqaProperties;
import com.acooly.portlets.fqa.core.dao.FqaDao;
import com.acooly.portlets.fqa.core.entity.Fqa;
import com.acooly.portlets.fqa.core.entity.FqaBody;
import com.acooly.portlets.fqa.core.service.FqaBodyService;
import com.acooly.portlets.fqa.core.service.FqaService;
import com.acooly.portlets.fqa.dto.FqaApplyInfo;
import com.acooly.portlets.fqa.dto.FqaInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * p_fqa Service实现
 *
 * @author zhangpu@acooly.cn
 * @date 2020-02-26 12:31:54
 */
@Service("fqaService")
public class FqaServiceImpl extends EntityServiceImpl<Fqa, FqaDao> implements FqaService {

    public static final String FQA_QUERY_CATCH_NAME = "acooly-portlets-fqa_cache";

    @Autowired
    private TreeTypeService treeTypeService;
    @Autowired
    private FqaBodyService fqaBodyService;


    @Override
    public FqaInfo apply(FqaApplyInfo fqaApplyInfo) {
        Fqa fqa = null;
        if (fqaApplyInfo.getId() == null) {
            fqa = save(fqaApplyInfo);
        } else {
            fqa = update(fqaApplyInfo);
        }
        return convertFqa(fqa);
    }

    @Override
    public Fqa save(FqaApplyInfo fqaApplyInfo) {
        Fqa fqa = new Fqa();
        BeanCopier.copy(fqaApplyInfo, fqa);
        fqa.setStatus(AbleStatus.enable);
        if (Strings.isNotBlank(fqa.getTypeCode())) {
            TreeType treeType = treeTypeService.findByCode(PortletFqaProperties.TREE_TYPE_SCHEME_FQA,
                    fqa.getTypeCode());
            if (treeType != null) {
                fqa.setTypeName(treeType.getName());
            }
        }
        save(fqa);
        FqaBody fqaBody = new FqaBody(fqa.getId(), fqaApplyInfo.getAnswer());
        fqaBodyService.save(fqaBody);
        fqa.setAnswer(fqaBody.getBody());
        return fqa;
    }

    @Override
    @CacheEvict(value = FQA_QUERY_CATCH_NAME ,key = "#fqaApplyInfo.id")
    public Fqa update(FqaApplyInfo fqaApplyInfo) {
        Fqa fqa = get(fqaApplyInfo.getId());
        BeanCopier.copy(fqaApplyInfo, fqa);
        // 如果修改了
        if (!Strings.equals(fqaApplyInfo.getTypeCode(), fqa.getTypeCode()) && Strings.isNotBlank(fqa.getTypeCode())) {
            TreeType treeType = treeTypeService.findByCode(PortletFqaProperties.TREE_TYPE_SCHEME_FQA,
                    fqa.getTypeCode());
            if (treeType != null) {
                fqa.setTypeName(treeType.getName());
            }
        }
        update(fqa);
        FqaBody fqaBody = new FqaBody(fqa.getId(), fqaApplyInfo.getAnswer());
        fqaBodyService.update(fqaBody);
        fqa.setAnswer(fqaBody.getBody());
        return fqa;
    }

    @Override
    @Cacheable(value = FQA_QUERY_CATCH_NAME, key = "#id")
    public FqaInfo detail(Long id) {
        Fqa fqa = get(id);
        if (fqa == null) {
            throw new BusinessException(CommonErrorCodes.OBJECT_NOT_EXIST);
        }
        FqaInfo fqaInfo = convertFqa(fqa);
        FqaBody fqaBody = fqaBodyService.get(id);
        if (fqaBody != null) {
            fqaInfo.setAnswer(fqaBody.getBody());
        }
        return fqaInfo;
    }

    @Override
    @CacheEvict(value = FQA_QUERY_CATCH_NAME, key = "#id")
    public void removeById(Serializable id) throws BusinessException {
        super.removeById(id);
    }


    @Override
    public PageInfo<FqaInfo> list(PageInfo<FqaInfo> pageInfo, Map<String, Object> map, Map<String, Boolean> sortMap) {
        PageInfo<Fqa> pageInfoFqa = new PageInfo<>(pageInfo.getCountOfCurrentPage(), pageInfo.getCurrentPage());
        query(pageInfoFqa, map, sortMap);
        List<FqaInfo> faqInfos = Lists.transform(pageInfoFqa.getPageResults(),
                f -> BeanCopier.copy(f, FqaInfo.class));
        pageInfo.setPageResults(faqInfos);
        pageInfo.setTotalCount(pageInfoFqa.getTotalCount());
        return pageInfo;
    }

    protected FqaInfo convertFqa(Fqa fqa) {
        FqaInfo fqaInfo = new FqaInfo();
        BeanCopier.copy(fqa, fqaInfo);
        return fqaInfo;
    }
}
