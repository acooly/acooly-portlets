/*
* acooly.cn Inc.
* Copyright (c) 2019 All Rights Reserved.
* create by zhangpu@acooly.cn
* date:2019-05-26
*/
package com.acooly.portlets.comment.client.dto;


import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.portlets.comment.client.enums.CommentAttachTypeEnum;
import com.acooly.portlets.comment.core.enums.CommentAttachTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
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
public class CommentAttachInfo extends ApiRequest {
    /**
     * 评论ID
     */
    @NotNull
	@Max(2147483646)
    @OpenApiField(desc = "评论ID", constraint = "评论ID", demo = "", ordinal = 2)
    private Long commentId;

    /**
     * 评论编码
     */
    @NotEmpty
	@Size(max = 64)
    @OpenApiField(desc = "评论编码", constraint = "评论编码", demo = "", ordinal = 3)
    private String commentNo;

    /**
     * 附件类型
     */
    @NotNull
    @OpenApiField(desc = "附件类型", constraint = "附件类型", ordinal = 4)
    private CommentAttachTypeEnum attachType;

    /**
     * 缩略图
     */
	@Size(max = 128)
    @OpenApiField(desc = "缩略图", constraint = "缩略图", demo = "", ordinal = 5)
    private String thumbnail;

    /**
     * 附件地址
     */
    @NotEmpty
	@Size(max = 128)
    @OpenApiField(desc = "附件地址", constraint = "附件地址", demo = "", ordinal = 6)
    private String attachPath;

    /**
     * 排序值
     */
	@Max(2147483646)
    @OpenApiField(desc = "排序值", constraint = "排序值", demo = "", ordinal = 7)
    private Long sortTime;

    /**
     * 备注
     */
	@Size(max = 128)
    @OpenApiField(desc = "备注", constraint = "备注", demo = "", ordinal = 10)
    private String comments;

}
