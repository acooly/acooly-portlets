/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.comment;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.portlets.comment.client.dto.CommentActionInfo;
import com.acooly.portlets.comment.client.dto.CommentActionResult;
import com.acooly.portlets.comment.client.dto.CommentBaseInfo;
import com.acooly.portlets.comment.client.enums.CommentLogActionTypeEnum;
import com.acooly.portlets.comment.core.service.CommentService;
import com.acooly.test.NoWebTestBase;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CommentServiceTest extends NoWebTestBase {


    @Autowired
    private CommentService commentService;

    String busiType = "hunlizhe-case";


    @Test
    public void testComment() {
        // 这个是顶层评论
        CommentBaseInfo commentBaseInfo = new CommentBaseInfo("1234567890", "zhangpu",
                "/profile/photo/1234567890", "192.168.0.1",
                "这个我评论的内容", "1");
        commentService.comment(commentBaseInfo);
    }

    @Test
    public void testSubComment() {
        CommentBaseInfo commentBaseInfo = new CommentBaseInfo("1234567890", "zhangpu",
                "这个我评论的子评论内容", busiType, "1", 3L);
        commentService.comment(commentBaseInfo);
    }

    @Test
    public void testThumbsup() {
        CommentActionInfo actionInfo = new CommentActionInfo(3L, CommentLogActionTypeEnum.thumbsup,
                "1234567891", "zhangpu1",
                "/profile/photo/1234567890", "192.168.0.1");
        CommentActionResult result = commentService.action(actionInfo);
        log.info("CommentActionResult: {}", result);
    }

    @Test
    public void testThumbsupCancel() {
        CommentActionInfo actionInfo = new CommentActionInfo(1L, CommentLogActionTypeEnum.thumbsup_cancel,
                "1234567891", "zhangpu1",
                "/profile/photo/1234567890", "192.168.0.1");
        CommentActionResult result = commentService.action(actionInfo);
        log.info("CommentActionResult: {}", result);
    }

    @Test
    public void testQuery() {
        PageInfo pageInfo = new PageInfo();
        commentService.query(pageInfo, "1234567891", "1", busiType);
        log.info("CommentService.Query: {}", JSON.toJSONString(pageInfo, true));
    }


}
