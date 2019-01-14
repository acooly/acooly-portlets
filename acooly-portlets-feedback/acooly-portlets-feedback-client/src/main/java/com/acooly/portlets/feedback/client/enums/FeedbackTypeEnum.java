/*
 * acooly.cn Inc.
 * Copyright (c) 2019 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2019-01-14
 *
 */
package com.acooly.portlets.feedback.client.enums;

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * 客户反馈 FeedbackTypeEnum 枚举定义
 *
 * @author zhangpu@acooly.cn
 * Date: 2019-01-14 21:23:31
 */
public enum FeedbackTypeEnum implements Messageable {

    suggest("suggest", "建议"),

    complaint("complaint", "投诉"),

    consult("consult", "咨询"),

    other("other", "其他");


    private final String code;
    private final String message;

    private FeedbackTypeEnum(String code, String message) {
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
        for (FeedbackTypeEnum type : values()) {
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
    public static FeedbackTypeEnum find(String code) {
        for (FeedbackTypeEnum status : values()) {
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
    public static List<FeedbackTypeEnum> getAll() {
        List<FeedbackTypeEnum> list = new ArrayList<FeedbackTypeEnum>();
        for (FeedbackTypeEnum status : values()) {
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
        for (FeedbackTypeEnum status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
