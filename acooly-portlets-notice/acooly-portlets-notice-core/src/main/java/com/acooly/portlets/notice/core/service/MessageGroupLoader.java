package com.acooly.portlets.notice.core.service;

import com.acooly.core.common.boot.Apps;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author zhangpu
 * @date 2019-01-11 18:04
 */
@Slf4j
@Component
public class MessageGroupLoader implements InitializingBean {

    Map<String, String> messageGroups = Maps.newHashMap();

    public Map<String, String> loadMessageGroups() {
        return messageGroups;
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        Map<String, MessageGroup> beans = Apps.getApplicationContext().getBeansOfType(MessageGroup.class);
        if (beans == null || beans.size() == 0) {
            return;
        }

        for (MessageGroup messageGroup : beans.values()) {
            messageGroups.putAll(messageGroup.mapping());
        }

    }
}
