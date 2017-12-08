/*
* acooly.cn Inc.
* Copyright (c) 2017 All Rights Reserved.
* create by acooly
* date:2017-11-23
*/
package com.acooly.portlets.notice.core.entity;


import com.acooly.core.common.domain.AbstractEntity;
import com.acooly.portlets.notice.core.enums.NoticeContentTypeEnum;
import com.acooly.portlets.notice.core.enums.NoticeStatusEnum;
import com.acooly.portlets.notice.core.enums.NoticeTypeEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * 公告消息 Entity
 *
 * @author acooly
 * Date: 2017-11-23 00:53:06
 */
@Getter
@Setter
@Entity
@Table(name = "p_notice_info")
public class NoticeInfo extends AbstractEntity {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * 消息标题
     */
    @Size(max = 64)
    private String title;

    /**
     * 消息内容
     */
    @NotEmpty
    @Size(max  = 1024)
    private String content = "";

    /**
     * 内容类型
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    private NoticeContentTypeEnum contentType = NoticeContentTypeEnum.text;

    /**
     * 会话（附带的自定义结构体）
     */
    @Size(max = 255)
    private String context;

    /**
     * 定时发送时间
     */
    private Date schedulerTime;

    /**
     * 发送时间
     */
    private Date sendTime;

    /**
     * 发送人
     */
    @NotEmpty
    @Size(max = 32)
    private String sender = "";

    /**
     * 发送总数
     */
    private Integer sendTotal;

    /**
     * 已读总数
     */
    private Integer receivedTotal;

    /**
     * 状态
     */
    @Enumerated(EnumType.STRING)
    private NoticeStatusEnum status;

    /**
     * 消息类型
     */
    @Enumerated(EnumType.STRING)
    @NotNull
    private NoticeTypeEnum type = NoticeTypeEnum.group;


    /**
     * 备注
     */
    @Size(max = 255)
    private String comments;
    
    /**
     * 自定义分组
     */
    private String customGroup;
    
    /**
     * 消息接收人  如果是广播的话为ALL
     */
    private String receiver;
    
    /**
     * 该消息是否已读
     */
    private Boolean readed;
    
    /**
     * 是否需要推送
     */
    private Boolean push;

}
