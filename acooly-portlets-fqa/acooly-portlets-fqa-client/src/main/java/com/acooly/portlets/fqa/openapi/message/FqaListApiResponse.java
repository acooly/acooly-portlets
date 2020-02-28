/**
 * acooly-portlets
 * <p>
 * Copyright 2014 Acooly.cn, Inc. All rights reserved.
 *
 * @author zhangpu
 * @date 2020-02-28 10:59
 */
package com.acooly.portlets.fqa.openapi.message;

import com.acooly.openapi.framework.common.message.PageApiResponse;
import com.acooly.portlets.fqa.dto.FqaInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * FQA分页查询 响应报文
 *
 * @author zhangpu
 * @date 2020-02-28 10:59
 */
@Getter
@Setter
public class FqaListApiResponse extends PageApiResponse<FqaInfo> {
}
