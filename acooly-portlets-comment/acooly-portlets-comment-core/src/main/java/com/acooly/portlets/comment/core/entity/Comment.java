/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by acooly
 * date:2019-01-06
 */
package com.acooly.portlets.comment.core.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.core.utils.enums.WhetherStatus;
import com.acooly.portlets.comment.client.enums.CommentStatusEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Size;

/**
 * p_comment Entity
 *
 * @author acooly
 * Date: 2019-01-06 19:37:26
 */
@Entity
@Table(name = "p_comment")
@Getter
@Setter
public class Comment extends AbstractEntity {

    /**
     * 父ID
     */
    private Long parentId;

    /**
     * 评论编码
     */
    @Size(max = 64)
    private String commentNo;

    /**
     * path
     */
    @Size(max = 128)
    private String path;

    /**
     * 业务类型
     */
    @NotEmpty
    @Size(max = 32)
    private String busiType;

    /**
     * 业务标志
     */
    @NotEmpty
    @Size(max = 32)
    private String busiKey;

    /**
     * 评论人编号
     */
    @NotEmpty
    @Size(max = 64)
    private String userNo;

    /**
     * 评论人用户名
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
     * 标题
     */
    @Size(max = 45)
    private String title;

    /**
     * 评论内容
     */
    @NotEmpty
    @Size(max = 1024)
    private String content;

    /**
     * 原始评论内容
     */
    @Size(max = 1024)
    private String originContent;


    /**
     * 点赞数
     */
    private Integer thumbsup = 0;

    /**
     * 复评数
     */
    private int repeats = 0;

    /**
     * 附件数
     */
    private int attachCount;

    /**
     * 星级
     */
    @Max(5)
    private int star;

    /**
     * 是否置顶
     */
    @Enumerated(EnumType.STRING)
    private WhetherStatus sticky = WhetherStatus.no;

    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
    private CommentStatusEnum status = CommentStatusEnum.enable_publish;

    /**
     * comments
     */
    @Size(max = 128)
    private String comments;

}
