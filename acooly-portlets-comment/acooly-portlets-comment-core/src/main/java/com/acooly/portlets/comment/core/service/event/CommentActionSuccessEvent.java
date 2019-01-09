package com.acooly.portlets.comment.core.service.event;

import com.acooly.portlets.comment.client.dto.CommentInfo;
import com.acooly.portlets.comment.client.dto.CommentLogInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 评论发布事件
 *
 * @author zhangpu
 * @date 2019-01-08 11:46
 */
@Slf4j
@Data
public class CommentActionSuccessEvent {

    /**
     * 被操作评论
     */
    private CommentInfo commentInfo;

    /**
     * 操作日志
     */
    private CommentLogInfo commentLogInfo;


}
