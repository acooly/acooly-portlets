package com.acooly.portlets.fqa.openapi;

import com.acooly.openapi.framework.client.AbstractApiClientService;
import com.acooly.openapi.framework.common.message.ApiResponse;
import com.acooly.portlets.fqa.openapi.message.FqaApplyApiRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author zhangpu
 * @date 2019-01-20 23:42
 */
@Slf4j
@Component
public class FqaApiClientService extends AbstractApiClientService {

    ApiResponse feedbackApply(FqaApplyApiRequest request) {
        return request(request, ApiResponse.class);
    }

}
