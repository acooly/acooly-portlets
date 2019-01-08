package com.acooly.portlets.comment.core.service;

import com.acooly.portlets.comment.client.dto.CommentBaseInfo;
import com.acooly.portlets.comment.client.dto.CommentInfo;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 评论发布成功事件
 *
 * @author zhangpu
 * @date 2019-01-08 11:46
 */
@Slf4j
@Data
public class CommentSuccessEvent {

    /**
     * 请求参数
     */
    private CommentBaseInfo commentBaseInfo;

    /**
     * 复评时被评论的父评论信息
     */
    private CommentInfo parent;

    /**
     * 结果参数
     */
    private CommentInfo commentInfo;


}
