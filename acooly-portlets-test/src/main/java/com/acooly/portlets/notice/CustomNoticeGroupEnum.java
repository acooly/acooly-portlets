package com.acooly.portlets.notice;

/**
 * 通知消息分组
 *
 * @author zhangpu
 * @date 2019-01-01 15:08
 */

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum CustomNoticeGroupEnum implements Messageable {

    SYSTEM("SYSTEM", "系统公告"),

    ACTIVITY("ACTIVITY", "平台活动"),

    BUSINESS("BUSINESS", "我的消息"),

    BUSINESS_BUY("BUSINESS_BUY", "购买"),

    BUSINESS_NEWS("BUSINESS_NEWS", "动态"),

    BUSINESS_COMMENT("BUSINESS_COMMENT", "评论"),

    BUSINESS_THUMB_UP("BUSINESS_THUMB_UP", "点赞");


    private final String code;
    private final String message;

    CustomNoticeGroupEnum(String code, String message) {
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
        for (CustomNoticeGroupEnum type : values()) {
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
    public static CustomNoticeGroupEnum find(String code) {
        for (CustomNoticeGroupEnum status : values()) {
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
    public static List<CustomNoticeGroupEnum> getAll() {
        List<CustomNoticeGroupEnum> list = new ArrayList<CustomNoticeGroupEnum>();
        for (CustomNoticeGroupEnum status : values()) {
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
        for (CustomNoticeGroupEnum status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
