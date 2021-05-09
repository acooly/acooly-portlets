/*
 * acooly.cn Inc.
 * Copyright (c) 2021 All Rights Reserved.
 * create by zhangpu
 * date:2021-05-08
 */
package com.acooly.portlets.successtory.core.service.impl;

import org.springframework.stereotype.Service;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.portlets.successtory.core.service.SuccessStoryService;
import com.acooly.portlets.successtory.core.dao.SuccessStoryDao;
import com.acooly.portlets.successtory.core.entity.SuccessStory;

/**
 * p_success_story Service实现
 *
 * @author zhangpu
 * @date 2021-05-08 18:12:01
 */
@Service("successStoryService")
public class SuccessStoryServiceImpl extends EntityServiceImpl<SuccessStory, SuccessStoryDao> implements SuccessStoryService {

}
