/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-02-28 11:02
 */
package com.acooly.portlets.fqa.openapi.message;

import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.ApiRequest;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * FQA发布 请求报文
 *
 * @author zhangpu
 * @date 2020-02-28 11:02
 */
@Getter
@Setter
public class FqaApplyApiRequest extends ApiRequest {

    /**
     * 问题
     */
    @NotBlank
    @Size(max = 128)
    @OpenApiField(desc = "问题", constraint = "问题", demo = "什么是卖家ID", ordinal = 2)
    private String ask;

    /**
     * 答案
     */
    @NotBlank
    @Size(max = 1024)
    @OpenApiField(desc = "答案", constraint = "答案", demo = "卖家ID指的是收款方ID，可以是个人或企业会员ID。", ordinal = 3)
    private String question;

    /**
     * 作者
     */
    @Size(max = 16)
    @OpenApiField(desc = "作者", constraint = "作者", demo = "zhangpu", ordinal = 4)
    private String author;

    /**
     * 问题分类
     */
    @Size(max = 32)
    @OpenApiField(desc = "问题分类编码", constraint = "问题分类，根据独立多级分类模块自定义", demo = "0001", ordinal = 5)
    private String askTypeCode;


}
