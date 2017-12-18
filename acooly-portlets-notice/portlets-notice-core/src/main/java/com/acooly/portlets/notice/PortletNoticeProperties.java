/*
 *
 * www.prosysoft.com Inc.
 * Copyright (c) 2017  All Rights Reserved
 */

package com.acooly.portlets.notice;

import com.acooly.core.utils.validate.Validators;
import com.acooly.portlets.notice.core.push.providers.PushProviderEnums;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;

import javax.validation.constraints.NotNull;

/**
 * Created by liubin@prosysoft.com on 2017/12/6.
 */
@Getter
@Setter
@ConfigurationProperties(prefix = PortletNoticeProperties.PREFIX)
public class PortletNoticeProperties implements InitializingBean {
	public static final String PREFIX = "acooly.portlets.notice";
	/**
	 * 是否启用该组件
	 */
	private Boolean enable = true;
	
	/**
	 * 文件存储路径
	 */
	private String storagePath = "app";
	
	/**
	 * 消息发送渠道提供商
	 */
	@NotNull
	private PushProviderEnums pushProvider;
	
	/**
	 * 网关地址
	 */
	@NotBlank
	private String gateway;
	
	/** 离线消息保留时间,单位秒，默认1天(86400)，0不保存，最大10天 */
	private int timeToLive = 86400;
	
	/**
	 * 是否开启推送功能 默认开启
	 */
	private boolean push = true;
	
	private JPush jpush = new JPush ();
	
	private UMeng umeng = new UMeng ();
	
	@Override
	public void afterPropertiesSet () throws Exception {
		if(PushProviderEnums.UMENG.equals (pushProvider)){
			Validators.assertJSR303 (umeng);
		}
		else{
			Validators.assertJSR303 (jpush);
		}
	}
	
	
	@Data
	public static class JPush{
		/**
		 * 应用id
		 */
		@NotBlank
		private String appKey;
		/**
		 * 密钥
		 */
		@NotBlank
		private String masterSecret;
	}
	
	
	@Data
	public static class  UMeng{
		/**
		 * 应用id
		 */
		@NotBlank
		private String androidAppKey;
		/**
		 * 密钥
		 */
		@NotBlank
		private String androidMasterSecret;
		
		/**
		 * 应用id
		 */
		@NotBlank
		private String iosAppKey;
		/**
		 * 密钥
		 */
		@NotBlank
		private String iosMasterSecret;
	}
	
	
}
