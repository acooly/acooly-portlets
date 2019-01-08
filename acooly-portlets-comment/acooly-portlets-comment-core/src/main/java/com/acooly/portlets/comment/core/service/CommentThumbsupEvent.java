package com.acooly.portlets.comment.core.service;

import com.acooly.portlets.comment.client.dto.CommentInfo;
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
public class CommentThumbsupEvent {

    /**
     * 请求参数
     */
    private Long commentId;

    /**
     * 结果参数
     */
    private CommentInfo commentInfo;


}
