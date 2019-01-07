package com.acooly.portlets.comment.client.dto;

import com.acooly.core.utils.enums.WhetherStatus;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.portlets.comment.client.enums.CommentStatusEnum;
import com.google.common.collect.Lists;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author zhangpu
 * @date 2019-01-06 19:53
 */
@Slf4j
@Data
public class CommentInfo extends CommentBaseInfo {

    /**
     * 评论ID
     * 查询时有效，添加时为空
     */
    @OpenApiField(desc = "评论ID", constraint = "评论唯一标志，查询时有效，添加时为空", demo = "11")
    @NotNull
    private Long id;

    @OpenApiField(desc = "点赞数", constraint = "点赞数量", demo = "11")
    private int thumbsup = 0;

    @OpenApiField(desc = "复评数", constraint = "子评论数量", demo = "0")
    private int repeats = 0;

    @OpenApiField(desc = "状态", constraint = "评论状态", demo = "apply")
    @NotNull
    private CommentStatusEnum status;

    @OpenApiField(desc = "状态说明", constraint = "评论状态说明", demo = "已评论")
    @NotEmpty
    private String statusText;

    @OpenApiField(desc = "评论时间", constraint = "评论时间 yyyy-MM-dd HH:mm:ss", demo = "2019-01-01 02:02:02")
    @NotNull
    private Date pubDate;

    @OpenApiField(desc = "是否置顶", constraint = "是否置顶")
    @NotNull
    private WhetherStatus sticky;

    @OpenApiField(desc = "复评列表", constraint = "复评列表")
    private List<CommentInfo> children = Lists.newArrayList();

}
