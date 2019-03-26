/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-01-10
 *
 */
package com.acooly.portlets.alog.client.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 访问日志 ActionChannel 枚举定义
 *
 * @author zhangpu@acooly.cn
 * Date: 2019-01-10 18:10:55
 */
public enum ActionChannel implements Messageable {

    MicroMessenger("MicroMessenger", "微信"),

    MiniProgram("MiniProgram", "微信小程序"),

    H5("H5", "手机网页"),

    Web("Web", "电脑网站"),

    App("App", "手机App"),

    Other("Other", "其他"),;

    private final String code;
    private final String message;

    private ActionChannel(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String code() {
        return code;
    }

    @Override
    public String message() {
        return message;
    }


    public static Map<String, String> mapping() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (ActionChannel type : values()) {
            map.put(type.getCode(), type.getMessage());
        }
        return map;
    }

    /**
     * 通过枚举值码查找枚举值。
     *
     * @param code 查找枚举值的枚举值码。
     * @return 枚举值码对应的枚举值。
     * @throws IllegalArgumentException 如果 code 没有对应的 Status 。
     */
    public static ActionChannel find(String code) {
        for (ActionChannel status : values()) {
            if (status.getCode().equals(code)) {
                return status;
            }
        }
        return null;
    }

    /**
     * 获取全部枚举值。
     *
     * @return 全部枚举值。
     */
    public static List<ActionChannel> getAll() {
        List<ActionChannel> list = new ArrayList<ActionChannel>();
        for (ActionChannel status : values()) {
            list.add(status);
        }
        return list;
    }

    /**
     * 获取全部枚举值码。
     *
     * @return 全部枚举值码。
     */
    public static List<String> getAllCode() {
        List<String> list = new ArrayList<String>();
        for (ActionChannel status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
