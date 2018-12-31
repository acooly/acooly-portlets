/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice.facade.dto;

import com.acooly.core.common.facade.DtoBase;
import com.acooly.core.common.facade.InfoBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;

/**
 * Created by liubin@prosysoft.com on 2017/12/16.
 */
@Setter
@Getter
public class NoticeReadDto extends InfoBase {

    public NoticeReadDto() {
    }

    public NoticeReadDto(String receiver, Long noticeId) {
        this.receiver = receiver;
        this.noticeId = noticeId;
    }

    /**
	 * 消息接收人
	 */
	@NotBlank(message = "接收人不能为空")
	private String receiver;
	
	/**
	 * 消息id
	 */
	@NotNull(message = "消息id不能为空")
	private Long noticeId;
}
