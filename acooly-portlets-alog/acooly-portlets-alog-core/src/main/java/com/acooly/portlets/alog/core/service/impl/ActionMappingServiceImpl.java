/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-01-10
 */
package com.acooly.portlets.alog.core.service.impl;

import com.acooly.core.common.exception.BusinessException;
import com.acooly.core.common.exception.CommonErrorCodes;
import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.core.utils.Assert;
import com.acooly.core.utils.Collections3;
import com.acooly.portlets.alog.core.dao.ActionMappingDao;
import com.acooly.portlets.alog.core.entity.ActionMapping;
import com.acooly.portlets.alog.core.service.ActionMappingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * 访问映射 Service实现
 * <p>
 * Date: 2019-01-10 18:10:55
 *
 * @author zhangpu@acooly.cn
 */
@Slf4j
@Service("actionMappingService")
public class ActionMappingServiceImpl extends EntityServiceImpl<ActionMapping, ActionMappingDao> implements ActionMappingService {


    @Override
    @Cacheable(cacheNames = "alogActionMapping", key = "#actionKey")
    public ActionMapping getActionMapping(String actionKey) {
        List<ActionMapping> actionMappings = getEntityDao().findByActionKey(actionKey);
        if (Collections3.isEmpty(actionMappings)) {
            return null;
        }
        log.info("load ActionMapping from database. actionKey:{}", actionKey);
        return Collections3.getFirst(actionMappings);
    }

    @CachePut(cacheNames = "alogActionMapping", key = "#actionMapping.actionKey")
    @Override
    public void save(ActionMapping actionMapping) throws BusinessException {
        Assert.notNull(actionMapping);
        Assert.hasLength(actionMapping.getActionKey());
        List<ActionMapping> actionMappings = getEntityDao().findByActionKey(actionMapping.getActionKey());
        if (!Collections3.isEmpty(actionMappings)) {
            throw new BusinessException(CommonErrorCodes.OBJECT_NOT_UNIQUE, "映射配置actionKey已存在");
        }
        super.save(actionMapping);
    }

    @Override
    public void removeById(Serializable id) throws BusinessException {
        remove(get(id));
    }

    @Override
    @CacheEvict(cacheNames = "alogActionMapping", key = "#actionMapping.actionKey")
    public void remove(ActionMapping actionMapping) throws BusinessException {
        super.remove(actionMapping);
    }

    @CachePut(cacheNames = "alogActionMapping", key = "#actionMapping.actionKey")
    @Override
    public void update(ActionMapping actionMapping) throws BusinessException {
        super.update(actionMapping);
    }
}
