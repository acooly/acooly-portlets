/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by acooly
 * date:2019-01-09
 */
package com.acooly.portlets.comment.core.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.portlets.comment.client.enums.CommentLogActionTypeEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 评论日志 Entity
 *
 * @author acooly
 * Date: 2019-01-09 00:46:41
 */
@Entity
@Table(name = "p_comment_log")
@Getter
@Setter
public class CommentLog extends AbstractEntity {

    /**
     * 所属评论
     */
    @NotNull
    private Long commentId;

    /**
     * 操作人
     */
    @Size(max = 64)
    private String userNo;

    /**
     * 操作人用户名
     */
    @Size(max = 45)
    private String userName;

    /**
     * 用户头像
     */
    @Size(max = 255)
    private String profilePhoto;

    /**
     * 请求IP
     */
    @Size(max = 32)
    private String userIp;

    /**
     * 操作类型
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    private CommentLogActionTypeEnum actionType;

    /**
     * 相关内容
     */
    @Size(max = 512)
    private String actionContent;

    /**
     * 备注
     */
    @Size(max = 128)
    private String comments;

}
