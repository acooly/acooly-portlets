/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-01-14
 */
package com.acooly.portlets.feedback.core.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.portlets.feedback.client.enums.FeedbackStatusEnum;
import com.acooly.portlets.feedback.client.enums.FeedbackTypeEnum;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotBlank;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 客户反馈 Entity
 *
 * @author zhangpu@acooly.cn
 * Date: 2019-01-14 21:23:31
 */
@Entity
@Table(name = "p_feedback")
@Getter
@Setter
public class Feedback extends AbstractEntity {

    /**
     * 类型
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    private FeedbackTypeEnum type;

    /**
     * 标题
     */
    @Size(max = 128)
    private String title;

    /**
     * 内容
     */
    @NotBlank
    @Size(max = 512)
    private String content;

    /**
     * 用户标志
     */
    @Size(max = 64)
    private String userKey;

    /**
     * 用户名
     */
    @Size(max = 32)
    private String userName;

    /**
     * 用户IP
     */
    @Size(max = 32)
    private String userIp;

    /**
     * 联系电话
     */
    @Size(max = 21)
    private String phoneNo;

    /**
     * 联系邮箱
     */
    @Size(max = 255)
    private String mail;

    /**
     * 联系地址
     */
    @Size(max = 128)
    private String address;

    /**
     * 联系信息
     */
    @Size(max = 255)
    private String contactInfo;

    /**
     * 回复内容
     */
    @Size(max = 512)
    private String replyContent;

    /**
     * 回复人
     */
    @Size(max = 32)
    private String replyUser;

    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
    private FeedbackStatusEnum status = FeedbackStatusEnum.apply;

    /**
     * 备注
     */
    @Size(max = 255)
    private String comments;

}
