package com.acooly.portlets.feedback.client.openapi;

import com.acooly.openapi.framework.client.AbstractApiClientService;
import com.acooly.portlets.feedback.client.openapi.message.FeedbackApplyApiRequest;
import com.acooly.portlets.feedback.client.openapi.message.FeedbackApplyApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhangpu
 * @date 2019-01-20 23:42
 */
@Slf4j
@Component
public class FeedbackApiClientService extends AbstractApiClientService {

    FeedbackApplyApiResponse feedbackApply(FeedbackApplyApiRequest request) {
        return request(request, FeedbackApplyApiResponse.class);
    }

}
