package com.acooly.portlets.comment.client.message;

import com.acooly.core.utils.enums.WhetherStatus;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.portlets.comment.client.enums.CommentBusiType;
import com.acooly.portlets.comment.client.enums.CommentStatusEnum;
import lombok.Data;
import javax.validation.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author zhangpu
 * @date 2019-01-07 00:53
 */
@Data
@Deprecated
public class CommentApiResponse extends ApiResponse {

    /**
     * 评论ID
     * 查询时有效，添加时为空
     */
    @OpenApiField(desc = "评论ID", constraint = "评论唯一标志，查询时有效，添加时为空", demo = "11", ordinal = 1)
    @NotNull
    private Long id;

    /**
     * 父节点
     * 复评时使用。可选
     */
    @OpenApiField(desc = "父节点ID", constraint = "父节点ID，定级评论为空", demo = "1", ordinal = 2)
    private Long parentId;


    /**
     * 用户编码
     */
    @Size(min = 10, max = 64)
    @NotBlank
    @OpenApiField(desc = "评论人编码", constraint = "业务层用户编码", demo = "201111112121212", ordinal = 3)
    private String userNo;

    /**
     * 用户名（冗余）
     */
    @Size(min = 1, max = 32)
    @OpenApiField(desc = "评论人用户名", constraint = "业务层用户名", demo = "acooly", ordinal = 4)
    private String userName;

    /**
     * 评论内容
     */
    @Size(min = 1, max = 1024)
    @NotBlank
    @OpenApiField(desc = "评论内容", constraint = "评论内容，支持剪短的多媒体内容评论",
            demo = "开启评论不要脸模式！这个<b>APP</b>写得非常棒！5分钟就配置好了！", ordinal = 5)
    private String content;

    /**
     * 自定义业务分类
     */
    @Size(max = 32)
    @NotBlank
    @OpenApiField(desc = "业务分类", constraint = "自定义业务分类", demo = "DEFAULT", ordinal = 6)
    private String busiType = CommentBusiType.DEFAULT.code();

    /**
     * 自定义业务ID（绑定ID），可选
     */
    @Size(min = 1, max = 32)
    @NotBlank
    @OpenApiField(desc = "业务KEY", constraint = "标记评论的业务,是业务的唯一标志", demo = "1", ordinal = 7)
    private String busiKey;


    @OpenApiField(desc = "点赞数", constraint = "点赞数量", demo = "11", ordinal = 8)
    private int thumbsup = 0;

    @OpenApiField(desc = "复评数", constraint = "子评论数量", demo = "0", ordinal = 9)
    private int repeats = 0;

    @OpenApiField(desc = "状态", constraint = "评论状态", demo = "apply", ordinal = 10)
    @NotNull
    private CommentStatusEnum status;

    @OpenApiField(desc = "状态说明", constraint = "评论状态说明", demo = "已评论", ordinal = 11)
    @NotBlank
    private String statusText;

    @OpenApiField(desc = "评论时间", constraint = "评论时间 yyyy-MM-dd HH:mm:ss", demo = "2019-01-01 02:02:02", ordinal = 12)
    @NotNull
    private Date pubDate;

    @OpenApiField(desc = "是否置顶", constraint = "是否置顶", ordinal = 13)
    @NotNull
    private WhetherStatus sticky;

}
