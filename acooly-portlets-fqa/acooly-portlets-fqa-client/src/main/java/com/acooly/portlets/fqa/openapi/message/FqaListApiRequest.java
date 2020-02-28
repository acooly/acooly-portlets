/*
 * acooly.cn Inc.
 * Copyright (c) 2020 All Rights Reserved.
 * create by zhangpu@acooly.cn
 * date:2020-02-25
 */
package com.acooly.portlets.fqa.openapi.message;


import com.acooly.core.utils.enums.WhetherStatus;
import com.acooly.openapi.framework.common.annotation.OpenApiField;
import com.acooly.openapi.framework.common.message.PageApiRequest;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * FQA ApiMessage
 * 注意：本类自定生成，不能直接使用，请开发人员根据业务选择下面的属性用于快速定义OpenApi的报文，
 * 主要减轻定义@OpenApiField和JSR303的工作。
 *
 * @author zhangpu@acooly.cn
 * Date: 2020-02-25 05:00:02
 */
@Getter
@Setter
public class FqaListApiRequest extends PageApiRequest {

    @NotNull
    @Size(max = 16)
    @OpenApiField(desc = "是否包含答案", constraint = "是否包含答案", demo = "yes", ordinal = 2)
    private WhetherStatus includeQuestion = WhetherStatus.yes;

}
