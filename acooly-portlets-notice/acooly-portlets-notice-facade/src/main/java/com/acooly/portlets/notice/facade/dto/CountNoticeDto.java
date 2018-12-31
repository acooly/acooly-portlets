/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2018  All Rights Reserved
 */

package com.acooly.portlets.notice.facade.dto;

import com.acooly.core.common.facade.InfoBase;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by liubin@qiudot.com on 2018-08-28 11:33.
 */
@Data
public class CountNoticeDto extends InfoBase {

    public CountNoticeDto() {
    }

    public CountNoticeDto(String receiver) {
        this.receiver = receiver;
    }

    public CountNoticeDto(String receiver, String customGroup) {
        this.receiver = receiver;
        this.customGroup = customGroup;
    }

    /**
     * 消息接收人
     */
    @NotBlank
    private String receiver;

    /**
     * 消息分组
     */
    private String customGroup;

}
