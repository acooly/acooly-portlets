package com.acooly.portlets.comment.client.enums;
/**
 * @author zhangpu
 * @date 2019-01-09 14:55
 */

import com.acooly.core.utils.enums.Messageable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public enum CommentErrorCodes implements Messageable {

    COMMENT_ALREADY_DISABLED("COMMENT_ALREADY_DISABLED", "评论已被禁用"),

    COMMENT_STATUS_CONFLICT("COMMENT_STATUS_CONFLICT", "状态冲突"),

    NOT_ALLOWED_THUMBSUP_YOURSELF("NOT_ALLOWED_THUMBSUP_YOURSELF", "不允许点赞自己的评论"),

    NOT_ALLOWED_THUMBSUP_REPEATED("NOT_ALLOWED_THUMBSUP_REPEATED", "不允许重复点赞");

    private final String code;
    private final String message;

    CommentErrorCodes(String code, String message) {
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
        for (CommentErrorCodes type : values()) {
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
    public static CommentErrorCodes find(String code) {
        for (CommentErrorCodes status : values()) {
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
    public static List<CommentErrorCodes> getAll() {
        List<CommentErrorCodes> list = new ArrayList<CommentErrorCodes>();
        for (CommentErrorCodes status : values()) {
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
        for (CommentErrorCodes status : values()) {
            list.add(status.code());
        }
        return list;
    }

}
