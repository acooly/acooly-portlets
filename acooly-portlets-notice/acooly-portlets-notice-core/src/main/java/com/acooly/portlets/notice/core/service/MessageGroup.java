package com.acooly.portlets.notice.core.service;

import java.util.Map;

/**
 * 自定义消息分类映射
 * <p>
 * 用于后台管理选择customGroup
 *
 * @author zhangpu
 * @date 2019-01-11 18:01
 */
public interface MessageGroup {

    /**
     * 分类映射信息
     *
     * @return
     */
    Map<String, String> mapping();

}
