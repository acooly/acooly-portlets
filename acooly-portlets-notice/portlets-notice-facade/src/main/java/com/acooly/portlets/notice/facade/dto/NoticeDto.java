/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */
package com.acooly.portlets.notice.facade.dto;


import com.acooly.core.common.facade.DtoBase;
import com.acooly.portlets.notice.facade.enums.NoticeContentTypeEnum;
import com.acooly.portlets.notice.facade.enums.NoticeStatusEnum;
import com.acooly.portlets.notice.facade.enums.PushTypeEnum;
import lombok.Getter;
import lombok.Setter;

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
public class NoticeDto extends DtoBase {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1L;


    /**
     * 消息标题
     */
    private String title;

    /**
     * 消息内容
     */
    private String content = "";

    /**
     * 内容类型
     */
    private NoticeContentTypeEnum contentType = NoticeContentTypeEnum.text;

    /**
     * 会话（附带的自定义结构体）
     */
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
    private NoticeStatusEnum status;

    /**
     * 消息类型
     */
    private PushTypeEnum type;


    /**
     * 备注
     */
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
    
    /**
     * 推送流水号  防止多次推送
     */
    private String pushNo;

}
