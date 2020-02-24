/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-05-26
 */
package com.acooly.portlets.comment.core.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.portlets.comment.client.enums.CommentAttachTypeEnum;
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
 * 评论附件 Entity
 *
 * @author zhangpu@acooly.cn
 * Date: 2019-05-26 23:54:02
 */
@Entity
@Table(name = "p_comment_attach")
@Getter
@Setter
public class CommentAttach extends AbstractEntity {

    /**
     * 评论ID
     */
    @NotNull
    private Long commentId;

    /**
     * 评论编码
     */
    @NotBlank
    @Size(max = 64)
    private String commentNo;

    /**
     * 附件类型
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    private CommentAttachTypeEnum attachType;

    /**
     * 缩略图
     */
    @Size(max = 128)
    private String thumbnail;

    /**
     * 附件地址
     */
    @NotBlank
    @Size(max = 128)
    private String attachPath;

    /**
     * 排序值
     */
    private Long sortTime;

    /**
     * 备注
     */
    @Size(max = 128)
    private String comments;

}
