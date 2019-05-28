package com.acooly.portlets.comment.client.dto;

import com.acooly.core.common.facade.DtoBase;
import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.annotation.OpenApiFieldCondition;
import com.acooly.portlets.comment.client.enums.CommentBusiType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author zhangpu
 * @date 2019-01-06 19:53
 */
@Slf4j
@Data
public class CommentBaseInfo extends DtoBase {


    /**
     * 评论编码（不填写则自动生成唯一标志）
     */
    @Size(min = 10, max = 64)
    @OpenApiField(desc = "评论编码", constraint = "评论编码（唯一标志）", demo = "123123123213", ordinal = 2)
    private String commentNo;

    /**
     * 父节点
     * 复评时使用。可选
     */
    @OpenApiField(desc = "父节点ID", constraint = "父节点ID，定级评论为空", demo = "1", ordinal = 3)
    private Long parentId;


    /**
     * 用户编码
     */
    @Size(min = 10, max = 64)
    @NotEmpty
    @OpenApiField(desc = "评论人编码", constraint = "业务层用户编码", demo = "201111112121212", ordinal = 4)
    private String userNo;

    /**
     * 用户名（冗余）
     */
    @Size(min = 1, max = 32)
    @OpenApiField(desc = "评论人用户名", constraint = "业务层用户名", demo = "acooly", ordinal = 5)
    private String userName;

    /**
     * 用户头像
     */
    @Size(max = 255)
    @OpenApiField(desc = "评论人头像", constraint = "评论人头像的URL", demo = "http://www.xxx.com/profilo/123123", ordinal = 6)
    private String profilePhoto;

    /**
     * 请求IP
     */
    @Size(max = 32)
    @OpenApiField(desc = "评论人IP", constraint = "评论人IP", demo = "218.201.89.76", ordinal = 7)
    private String userIp;

    /**
     * 评论内容
     */
    @Size(min = 1, max = 1024)
    @NotEmpty
    @OpenApiField(desc = "评论内容", constraint = "评论内容，支持剪短的多媒体内容评论",
            demo = "开启评论不要脸模式！这个<b>APP</b>写得非常棒！5分钟就配置好了！", ordinal = 8)
    private String content;

    /**
     * 自定义业务分类
     */
    @Size(max = 32)
    @NotEmpty
    @OpenApiField(desc = "业务分类", constraint = "自定义业务分类", demo = "DEFAULT", ordinal = 9)
    private String busiType = CommentBusiType.DEFAULT.code();

    /**
     * 自定义业务ID（绑定ID），可选
     */
    @Size(min = 1, max = 32)
    @NotEmpty
    @OpenApiField(desc = "业务KEY", constraint = "标记评论的业务,是业务的唯一标志", demo = "1", ordinal = 10)
    private String busiKey;

    /**
     * 星级
     */
    @Max(5)
    @OpenApiField(desc = "星级", constraint = "评论星级（1-5星）", demo = "5", ordinal = 11)
    private int star;

    @OpenApiFieldCondition("单笔查询()和创建有效，评论列表查询为空")
    @OpenApiField(desc = "附件", constraint = "评论的附件，支持最多10个，可为图片和视频。请采用专用接口上传附件获得缩略图和附件的相对地址", ordinal = 21)
    private List<CommentAttachInfo> attachInfos;


    public CommentBaseInfo() {
        setBizOrderNo(Ids.getDid());
    }

    /**
     * 发表评论专业构造
     *
     * @param userNo
     * @param userName
     * @param content
     * @param busiType
     * @param parentId
     */
    public CommentBaseInfo(String userNo, String userName, String content, String busiType, String busiKey, Long parentId) {
        this();
        this.parentId = parentId;
        this.userNo = userNo;
        this.userName = userName;
        this.content = content;
        this.busiType = busiType;
        this.busiKey = busiKey;
    }

    public CommentBaseInfo(String userNo, String userName, String profilePhoto, String userIp, String content, String busiKey) {
        this();
        this.userNo = userNo;
        this.userName = userName;
        this.profilePhoto = profilePhoto;
        this.userIp = userIp;
        this.content = content;
        this.busiKey = busiKey;
    }
}
