/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-26 17:19
 */
package com.acooly.portlets.test.comment;

import com.acooly.core.utils.Ids;
import com.acooly.openapi.framework.core.test.AbstractApiServieTests;
import com.acooly.portlets.comment.client.dto.CommentAttachInfo;
import com.acooly.portlets.comment.client.enums.CommentAttachTypeEnum;
import com.acooly.portlets.comment.client.enums.CommentBusiType;
import com.acooly.portlets.comment.client.message.CommentApiRequest;
import com.acooly.portlets.comment.client.message.CommentApiResponse;
import com.acooly.portlets.comment.client.message.CommentPublishApiRequest;
import com.acooly.portlets.comment.client.message.CommentPublishApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;

import java.util.List;

/**
 * @author zhangpu
 * @date 2019-03-26 17:19
 */
@Slf4j
public class CommentApiServiceTest extends AbstractApiServieTests {
    {
        gatewayUrl = "http://127.0.0.1:8080/gateway.do";
    }


    @Test
    public void testComment() {
        CommentApiRequest request = new CommentApiRequest();
        request.setUserNo(Ids.getDid());
        request.setUserName("acooly");
        request.setUserIp("211.12.1.12");
        request.setProfilePhoto("/aaa/aa/aa/a/png");
        request.setBusiType(CommentBusiType.DEFAULT.code());
        request.setBusiKey("1213");
        request.setContent("这里是发布的评论内容");
        request.setStar(4);
        CommentApiResponse response = request(request, CommentApiResponse.class);
        log.info("CommentApiResponse: {}", response);
    }

    @Test
    public void testCommentPublish() {
        CommentPublishApiRequest request = new CommentPublishApiRequest();
        request.setUserNo(Ids.getDid());
        request.setUserName("acooly");
        request.setUserIp("211.12.1.12");
//        request.setProfilePhoto("/aaa/aa/aa/a/png");
        request.setBusiType(CommentBusiType.DEFAULT.code());
        request.setBusiKey("1213");
        request.setContent("这里是发布的评论内容");
//        request.setStar(4);

        List<CommentAttachInfo> attachInfos = Lists.newArrayList();
        for (int i = 1; i <= 5; i++) {
            attachInfos.add(new CommentAttachInfo(CommentAttachTypeEnum.photo,
                    "/xxx/xxx/thumbnail" + i + ".jpg", "/xxx/xxx/attach" + i + ".jpg"));
        }
        request.setAttachInfos(attachInfos);
        request(request, CommentPublishApiResponse.class);
    }
}
