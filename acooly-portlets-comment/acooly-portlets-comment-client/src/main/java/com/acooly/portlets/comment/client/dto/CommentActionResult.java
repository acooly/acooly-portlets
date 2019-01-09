package com.acooly.portlets.comment.client.dto;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotNull;

/**
 * 评论发布事件
 *
 * @author zhangpu
 * @date 2019-01-08 11:46
 */
@Slf4j
@Data
public class CommentActionResult {

    /**
     * 被操作评论
     */
    @NotNull
    @OpenApiField(desc = "评论信息", constraint = "被操作的评论")
    private CommentInfo commentInfo;

    /**
     * 操作日志
     */
    @NotNull
    @OpenApiField(desc = "操作日志", constraint = "操作日志")
    private CommentLogInfo commentLogInfo;


    public CommentActionResult() {

    }

    public CommentActionResult(CommentInfo commentInfo, CommentLogInfo commentLogInfo) {
        this.commentInfo = commentInfo;
        this.commentLogInfo = commentLogInfo;
    }
}
