/**
 * create by zhangpu date:2017年11月23日
 */
package com.acooly.portlets.notice.core.dto;

import com.acooly.portlets.notice.facade.enums.CustomPushPropertyEnums;
import com.acooly.portlets.notice.facade.enums.DeviceTypeEnum;
import com.acooly.portlets.notice.facade.enums.NoticeContentTypeEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotBlank;

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
public class NoticeMessage {

    private static final String SYSTEM_SENDER = "system";

    /**
     * 消息标题
     */
    @NotBlank
    private String title;

    /**
     * 内容类型
     */
    private NoticeContentTypeEnum contentType = NoticeContentTypeEnum.MESSAGE;

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
     * 推送流水号 保证消息不会被重复推送 如果不传递组件内自动生成
     */
    private String pushNo;
    
    /**
     * 设备类型
     */
    private DeviceTypeEnum deviceType;


    public NoticeMessage() {
    }

    public NoticeMessage(String title, String content) {
        super();
        this.title = title;
        this.content = content;
    }

    public NoticeMessage(String title, String content, Map<String, Object> context) {
        super();
        this.title = title;
        this.content = content;
        this.context = context;
    }

}
