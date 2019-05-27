/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-05-26
 */
package com.acooly.portlets.comment.client.dto;


import com.acooly.core.common.facade.InfoBase;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.portlets.comment.client.enums.CommentAttachTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 评论附件信息
 *
 * @author zhangpu@acooly.cn
 * Date: 2019-05-26 23:54:02
 */
@Getter
@Setter
public class CommentAttachInfo extends InfoBase {

    /**
     * 附件类型
     */
    @NotNull
    @OpenApiField(desc = "附件类型", constraint = "附件类型,默认为photo", ordinal = 4)
    private CommentAttachTypeEnum attachType = CommentAttachTypeEnum.photo;

    /**
     * 缩略图
     */
    @Size(max = 128)
    @OpenApiField(desc = "缩略图", constraint = "缩略图", demo = "http://cdn.xxx.com/xxx/thumbnail.jpg", ordinal = 5)
    private String thumbnail;

    /**
     * 附件地址
     */
    @NotEmpty
    @Size(max = 128)
    @OpenApiField(desc = "附件地址", constraint = "附件地址", demo = "http://cdn.xxx.com/xxx/attach.jpg", ordinal = 6)
    private String attachPath;

    /**
     * 备注
     */
    @Size(max = 128)
    @OpenApiField(desc = "备注", constraint = "备注", demo = "这是现场照片", ordinal = 7)
    private String comments;

    public CommentAttachInfo() {
    }

    public CommentAttachInfo(@NotNull CommentAttachTypeEnum attachType, String thumbnail, String attachPath) {
        this.attachType = attachType;
        this.thumbnail = thumbnail;
        this.attachPath = attachPath;
    }

    public CommentAttachInfo(@NotNull CommentAttachTypeEnum attachType, String thumbnail, String attachPath, String comments) {
        this.attachType = attachType;
        this.thumbnail = thumbnail;
        this.attachPath = attachPath;
        this.comments = comments;
    }
}
