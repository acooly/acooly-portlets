package com.acooly.portlets.comment.client.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.portlets.comment.client.enums.CommentLogActionTypeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author zhangpu
 * @date 2019-01-07 00:56
 */
@Getter
@Setter
public class CommentActionApiRequest extends ApiRequest {
    /**
     * 评论ID
     * 查询时有效，添加时为空
     */
    @NotNull
    @OpenApiField(desc = "评论ID", constraint = "评论唯一标志", demo = "11", ordinal = 1)
    private Long commentId;

    @NotNull
    @OpenApiField(desc = "操作", constraint = "操作类型", ordinal = 2)
    private CommentLogActionTypeEnum actionType;

    /**
     * 用户编码
     */
    @Size(min = 1, max = 64)
    @NotBlank
    @OpenApiField(desc = "用户标志", constraint = "业务层用户编码", demo = "201111112121212", ordinal = 3)
    private String userNo;

    /**
     * 用户名（冗余）
     */
    @Size(max = 32)
    @OpenApiField(desc = "用户名称", constraint = "业务层用户名", demo = "acooly", ordinal = 4)
    private String userName;


    /**
     * 用户头像（冗余）
     */
    @Size(max = 255)
    @OpenApiField(desc = "用户头像", constraint = "业务层用户名", demo = "acooly", ordinal = 5)
    private String profilePhoto;

    /**
     * 请求IP
     */
    @Size(max = 32)
    @OpenApiField(desc = "用户IP", constraint = "请求操作的用户IP", demo = "218.210.99.78", ordinal = 6)
    private String userIp;

    /**
     * 评论内容
     */
    @Size(max = 512)
    @OpenApiField(desc = "附加内容", constraint = "评论内容，支持剪短的多媒体内容评论",
            demo = "开启评论不要脸模式！这个<b>APP</b>写得非常棒！5分钟就配置好了！", ordinal = 7)
    private String actionContent;

}
