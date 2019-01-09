package com.acooly.portlets.comment.client.openapi;

import com.acooly.openapi.framework.client.AbstractApiClientService;
import com.acooly.portlets.comment.client.message.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhangpu
 * @date 2019-01-08 02:55
 */
@Slf4j
@Component
public class CommentApiClientServiceImpl extends AbstractApiClientService implements CommentApiClientService {

    @Override
    public CommentApiResponse comment(CommentApiRequest request) {
        return request(request, CommentApiResponse.class);
    }

    @Override
    public CommentActionApiResponse commentAction(CommentActionApiRequest request) {
        return request(request, CommentActionApiResponse.class);
    }

    @Override
    public CommentListApiResponse commentList(CommentListApiRequest request) {
        return request(request, CommentListApiResponse.class);
    }
}
