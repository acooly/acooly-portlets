/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-03-23
 *
 */
package com.acooly.portlets.alog.analysis.persist.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 访问量统计 ActionVisitsTypeEnum 枚举定义
 *
 * @author zhangpu@acooly.cn
 * Date: 2019-03-23 16:38:18
 */
public enum ActionVisitsTypeEnum implements Messageable {

    PV("PV", "点击量"),

    UV("UV", "用户量"),

    IP("IP", "独立IP数"),;

    private final String code;
    private final String message;

    private ActionVisitsTypeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String code() {
        return code;
    }

    public String message() {
        return message;
    }

    public static Map<String, String> mapping() {
        Map<String, String> map = new LinkedHashMap<String, String>();
        for (ActionVisitsTypeEnum type : values()) {
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
    public static ActionVisitsTypeEnum find(String code) {
        for (ActionVisitsTypeEnum status : values()) {
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
    public static List<ActionVisitsTypeEnum> getAll() {
        List<ActionVisitsTypeEnum> list = new ArrayList<ActionVisitsTypeEnum>();
        for (ActionVisitsTypeEnum status : values()) {
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
        for (ActionVisitsTypeEnum status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
