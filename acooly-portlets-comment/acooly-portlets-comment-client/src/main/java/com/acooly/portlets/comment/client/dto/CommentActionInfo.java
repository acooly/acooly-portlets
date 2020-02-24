package com.acooly.portlets.comment.client.dto;

import com.acooly.core.common.facade.DtoBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.portlets.comment.client.enums.CommentLogActionTypeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author zhangpu
 * @date 2019-01-06 19:53
 */
@Slf4j
@Getter
@Setter
public class CommentActionInfo extends DtoBase {

    /**
     * 评论ID
     * 查询时有效，添加时为空
     */
    @NotNull
    @OpenApiField(desc = "评论ID", constraint = "评论唯一标志", demo = "11")
    private Long commentId;

    @NotNull
    @OpenApiField(desc = "操作", constraint = "操作类型")
    private CommentLogActionTypeEnum actionType;

    /**
     * 用户编码
     */
    @Size(min = 1, max = 64)
    @NotBlank
    @OpenApiField(desc = "用户标志", constraint = "业务层用户编码", demo = "201111112121212")
    private String userNo;

    /**
     * 用户名（冗余）
     */
    @Size(max = 32)
    @OpenApiField(desc = "用户名称", constraint = "业务层用户名", demo = "acooly")
    private String userName;


    /**
     * 用户头像（冗余）
     */
    @Size(max = 255)
    @OpenApiField(desc = "用户头像", constraint = "业务层用户名", demo = "acooly")
    private String profilePhoto;

    /**
     * 请求IP
     */
    @Size(max = 32)
    @OpenApiField(desc = "用户IP", constraint = "请求操作的用户IP", demo = "218.210.99.78")
    private String userIp;

    /**
     * 评论内容
     */
    @Size(max = 512)
    @OpenApiField(desc = "附加内容", constraint = "评论内容，支持剪短的多媒体内容评论", demo = "开启评论不要脸模式！这个<b>APP</b>写得非常棒！5分钟就配置好了！")
    private String actionContent;


    public CommentActionInfo() {
        setBizOrderNo(Ids.getDid());
    }


    public CommentActionInfo(Long commentId, CommentLogActionTypeEnum actionType, String userNo, String userName, String profilePhoto, String userIp,
                             String actionContent) {
        this();
        this.commentId = commentId;
        this.actionType = actionType;
        this.userNo = userNo;
        this.userName = userName;
        this.profilePhoto = profilePhoto;
        this.userIp = userIp;
        this.actionContent = actionContent;
    }

    public CommentActionInfo(Long commentId, CommentLogActionTypeEnum actionType, String userNo, String userName, String profilePhoto, String userIp) {
        this(commentId, actionType, userNo, userName, profilePhoto, userIp, null);
    }
}
