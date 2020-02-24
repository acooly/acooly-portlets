/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2019-03-26 17:19
 */
package com.acooly.portlets.test.comment;

import com.acooly.core.utils.enums.WhetherStatus;
import com.acooly.openapi.framework.core.test.AbstractApiServieTests;
import com.acooly.portlets.comment.client.dto.CommentAttachInfo;
import com.acooly.portlets.comment.client.enums.CommentAttachTypeEnum;
import com.acooly.portlets.comment.client.enums.CommentBusiType;
import com.acooly.portlets.comment.client.message.*;
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

    String userNo = "19052722441672320001";
    String username = "acooly";
    String busiKey = "1213";


    @Test
    public void testCommentPublish() {
        CommentPublishApiRequest request = new CommentPublishApiRequest();
        request.setUserNo(userNo);
        request.setUserName(username);
        request.setUserIp("211.12.1.12");
        request.setProfilePhoto("/aaa/aa/aa/a/png");
        request.setBusiType(CommentBusiType.DEFAULT.code());
        request.setBusiKey(busiKey);
        request.setContent("这里是发布的评论内容");
        request.setStar(4);

        List<CommentAttachInfo> attachInfos = Lists.newArrayList();
        for (int i = 1; i <= 5; i++) {
            attachInfos.add(new CommentAttachInfo(CommentAttachTypeEnum.photo,
                    "/xxx/xxx/thumbnail" + i + ".jpg", "/xxx/xxx/attach" + i + ".jpg"));
        }
        request.setAttachInfos(attachInfos);
        request(request, CommentPublishApiResponse.class);
    }

    @Test
    public void testSubCommentPublish() {
        CommentPublishApiRequest request = new CommentPublishApiRequest();
        request.setUserNo(userNo);
        request.setUserName(username);
        request.setParentId(1L);
        request.setUserIp("211.12.1.12");
        request.setProfilePhoto("/aaa/aa/aa/a/png");
        request.setBusiType(CommentBusiType.DEFAULT.code());
        request.setBusiKey(busiKey);
        request.setContent("这里是发布的评子论内容");
        request.setStar(4);

        List<CommentAttachInfo> attachInfos = Lists.newArrayList();
        for (int i = 1; i <= 3; i++) {
            attachInfos.add(new CommentAttachInfo(CommentAttachTypeEnum.photo,
                    "/xxx/xxx/thumbnail" + i + ".jpg", "/xxx/xxx/attach" + i + ".jpg"));
        }
        request.setAttachInfos(attachInfos);
        request(request, CommentPublishApiResponse.class);
    }

    @Test
    public void testCommentInfo() {
        CommentInfoApiRequest request = new CommentInfoApiRequest();
        request.setCommentId(1L);
        request(request, CommentInfoApiResponse.class);
    }


    @Test
    public void testCommentList() {
        CommentListApiRequest request = new CommentListApiRequest();
        request.setBusiKey(busiKey);
        request.setChildrenInclude(WhetherStatus.no);
        request.setAttachQuery(WhetherStatus.no);
        request(request, CommentListApiResponse.class);

    }
}
