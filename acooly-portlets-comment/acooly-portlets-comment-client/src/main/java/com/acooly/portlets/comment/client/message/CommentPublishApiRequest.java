package com.acooly.portlets.comment.client.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiFieldCondition;
import com.acooly.openapi.framework.common.message.ApiRequest;
import com.acooly.portlets.comment.client.dto.CommentAttachInfo;
import com.acooly.portlets.comment.client.enums.CommentBusiType;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 评论发布 请求报文
 *
 * @author zhangpu
 * @date 2019-01-07 00:51
 */
@Data
public class CommentPublishApiRequest extends ApiRequest {

    /**
     * 自定义业务分类
     */
    @Size(max = 32)
    @NotEmpty
    @OpenApiField(desc = "业务分类", constraint = "自定义业务分类", demo = "DEFAULT", ordinal = 1)
    private String busiType = CommentBusiType.DEFAULT.code();

    /**
     * 自定义业务ID（绑定ID），可选
     */
    @Size(min = 1, max = 32)
    @NotEmpty
    @OpenApiField(desc = "业务KEY", constraint = "标记评论的业务,是业务的唯一标志", demo = "1", ordinal = 2)
    private String busiKey;

    /**
     * 用户编码
     */
    @Size(min = 10, max = 64)
    @NotEmpty
    @OpenApiField(desc = "评论人编码", constraint = "业务层用户编码", demo = "201111112121212", ordinal = 3)
    private String userNo;

    /**
     * 评论内容
     */
    @Size(min = 1, max = 1024)
    @NotEmpty
    @OpenApiField(desc = "评论内容", constraint = "评论内容，支持剪短的多媒体内容评论",
            demo = "开启评论不要脸模式！这个<b>APP</b>写得非常棒！5分钟就配置好了！", ordinal = 4)
    private String content;

    /**
     * 父节点
     * 复评时使用。可选
     */
    @OpenApiFieldCondition("复评必选")
    @OpenApiField(desc = "父节点ID", constraint = "父节点ID，定级评论为空,子评论（复评）时候必须填写", demo = "1", ordinal = 5)
    private Long parentId;


    /**
     * 用户名（冗余）
     */
    @Size(max = 32)
    @OpenApiField(desc = "评论人用户名", constraint = "业务层用户名", demo = "acooly", ordinal = 6)
    private String userName;

    /**
     * 用户头像（冗余）
     */
    @Size(max = 255)
    @OpenApiField(desc = "用户头像", constraint = "业务层用户名", demo = "acooly", ordinal = 7)
    private String profilePhoto;

    /**
     * 请求IP
     */
    @Size(max = 32)
    @OpenApiField(desc = "用户IP", constraint = "请求操作的用户IP", demo = "218.210.99.78", ordinal = 8)
    private String userIp;


    /**
     * 星级
     */
    @Max(5)
    @OpenApiField(desc = "星级", constraint = "评论星级（1-5星）", demo = "5", ordinal = 9)
    private int star;


    @OpenApiField(desc = "附件", constraint = "评论的附件，支持最多10个，可为图片和视频。请采用专用接口上传附件获得缩略图和附件的相对地址", ordinal = 10)
    @Size(max = 10)
    private List<CommentAttachInfo> attachInfos;

}
