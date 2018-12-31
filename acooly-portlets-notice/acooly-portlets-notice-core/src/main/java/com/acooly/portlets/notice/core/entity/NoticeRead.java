/*
* acooly.cn Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by acooly
* date:2017-11-23
*/
package com.acooly.portlets.notice.core.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.core.utils.enums.AbleStatus;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 * 公告消息读取状态 Entity
 *
 * @author acooly
 * Date: 2017-11-23 00:53:06
 */
@Getter
@Setter
@Entity
@Table(name = "p_notice_read")
public class NoticeRead extends AbstractEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * 接收人
     */
    @NotEmpty
    @Size(max = 32)
    private String receiver = "";

    /**
     * 广播已读
     */
    @Size(max = 2048)
    private String broadcastRead;

    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
    private AbleStatus status = AbleStatus.enable;


    /**
     * 备注
     */
    @Size(max = 255)
    private String comments;

}
