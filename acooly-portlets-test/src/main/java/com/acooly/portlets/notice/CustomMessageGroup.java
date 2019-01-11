package com.acooly.portlets.notice;

import com.acooly.portlets.notice.core.service.MessageGroup;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhangpu
 * @date 2019-01-11 18:21
 */
@Slf4j
@Component
public class CustomMessageGroup implements MessageGroup {


    public Map<String, String> mapping() {
        return CustomNoticeGroupEnum.mapping();
    }
}
