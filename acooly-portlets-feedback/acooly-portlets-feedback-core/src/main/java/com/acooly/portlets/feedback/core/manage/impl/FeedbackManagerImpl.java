/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-01-14
 */
package com.acooly.portlets.feedback.core.manage.impl;

import com.acooly.core.common.service.EntityServiceImpl;
import com.acooly.portlets.feedback.core.dao.FeedbackDao;
import com.acooly.portlets.feedback.core.entity.Feedback;
import com.acooly.portlets.feedback.core.manage.FeedbackManager;
import org.springframework.stereotype.Service;

/**
 * 客户反馈 Service实现
 * <p>
 * Date: 2019-01-14 21:23:31
 *
 * @author zhangpu@acooly.cn
 */
@Service
public class FeedbackManagerImpl extends EntityServiceImpl<Feedback, FeedbackDao> implements FeedbackManager {

}
