/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

/**
 * create by zhangpu date:2017年11月23日
 */
package com.acooly.portlets.notice.facade.dto;

import com.acooly.core.common.exception.OrderCheckException;
import com.acooly.core.common.facade.DtoBase;
import com.acooly.portlets.notice.facade.enums.CustomPushPropertyEnums;
import com.acooly.portlets.notice.facade.enums.NoticeContentTypeEnum;
import com.acooly.portlets.notice.facade.enums.PushTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.util.Assert;

import java.util.List;

import java.util.Map;

/**
 * 通知消息 参数 Dto
 *
 * @author zhangpu
 * @date 2017年11月23日
 */
@Getter
@Setter
@ToString
public class NoticeMessage  extends DtoBase{

    private static final String SYSTEM_SENDER = "system";

    /**
     * 消息标题
     */
    @NotBlank
    private String title;

    /**
     * 内容类型
     */
    private NoticeContentTypeEnum contentType = NoticeContentTypeEnum.text;

    /**
     * 消息内容
     */
    @NotBlank
    private String content;

    /**
     * 发送人
     */
    private String sender = SYSTEM_SENDER;

    /**
     * 业务扩展参数
     * <p>
     * 可以用于业务扩展。比如：收到消息后，根据业务扩展参数引导业务流程等
     */
    private Map<String, Object> context;
    
    /**
     * 上层业务系统对消息按照自己的策略进行分组
     */
    private String customGroup;

    /**
     * 是否push
     * <p>
     * 该参数优先级大于配置文件参数
     */
    private boolean push = true;
    
    /**
     * 系统属性参数
     * <p>
     * 扩展属性，主要考虑在push等场景需要传递特殊参数。预留
     */
    private Map<CustomPushPropertyEnums, Object> properties;
    
    /**
     * 推送方式
     */
    private PushTypeEnum pushType;
    
    /**
     * 消息接收人
     */
    private List<String> targets;


    public NoticeMessage () {
    }

    public NoticeMessage (String title, String content) {
        super();
        this.title = title;
        this.content = content;
    }

    public NoticeMessage (String title, String content, Map<String, Object> context) {
        super();
        this.title = title;
        this.content = content;
        this.context = context;
    }
    
    @Override
    public void check () throws OrderCheckException {
        super.check ();
        if(push){
            Assert.notNull (pushType,"消息需要推送到app时，推送类型不能为空");
        }
        
        if(PushTypeEnum.GROUP.equals (pushType)){
            Assert.notEmpty (targets,"选择群发是消息接收人不能为空");
        }
    }
}
