/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.comment;

import com.acooly.core.common.dao.support.PageInfo;
import com.acooly.portlets.comment.client.dto.CommentBaseInfo;
import com.acooly.portlets.comment.core.service.CommentService;
import com.acooly.test.NoWebTestBase;
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
                "这个我评论的内容", busiType, "1", null);
        commentService.comment(commentBaseInfo);
    }

    @Test
    public void testSubComment() {
        CommentBaseInfo commentBaseInfo = new CommentBaseInfo("1234567890", "zhangpu",
                "这个我评论的子评论内容", busiType, "1", 1L);
        commentService.comment(commentBaseInfo);
    }

    @Test
    public void testThumbsup() {
        commentService.thumbsup(1L);
    }

    @Test
    public void testQuery() {
        PageInfo pageInfo = new PageInfo();
        commentService.query(pageInfo, "1", busiType);
        log.info(pageInfo.toString());
    }


}
