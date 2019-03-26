CREATE TABLE `p_action_log` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `action_key` varchar(512) NOT NULL COMMENT '操作',
  `action_name` varchar(32) DEFAULT NULL COMMENT '操作名称',
  `action_group` varchar(64) DEFAULT NULL,
  `action_url` varchar(512) DEFAULT NULL COMMENT 'URL连接',
  `user_key` varchar(64) DEFAULT NULL COMMENT '用户名',
  `cookies` varchar(128) DEFAULT NULL,
  `os` varchar(32) DEFAULT NULL,
  `channel` varchar(16) NOT NULL COMMENT '渠道',
  `channel_info` varchar(255) DEFAULT NULL COMMENT '渠道信息',
  `channel_version` varchar(16) DEFAULT NULL COMMENT '渠道版本',
  `data` varchar(512) DEFAULT NULL,
  `action_time` datetime DEFAULT NULL COMMENT '收集时间',
  `user_ip` varchar(16) DEFAULT NULL COMMENT '访问IP',
  `isp` varchar(16) DEFAULT NULL COMMENT '运营商',
  `country` varchar(32) DEFAULT NULL COMMENT '国家',
  `region` varchar(32) DEFAULT NULL COMMENT '省',
  `city` varchar(32) DEFAULT NULL COMMENT '市',
  `lnt_lat` varchar(32) DEFAULT NULL COMMENT '经纬度',
  `pv` int(11) DEFAULT '0' COMMENT '点击量',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `comments` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  KEY `idx_action_log_time_key` (`action_time`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='访问日志';


CREATE TABLE `p_action_mapping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `mapping_type` varchar(16) NOT NULL COMMENT '映射类型 {url:链接,openapi:API服务,other:其他}',
  `action_key` varchar(128) NOT NULL COMMENT '操作Key',
  `action_name` varchar(32) NOT NULL COMMENT '操作名称',
  `create_time` datetime NOT NULL,
  `update_time` datetime NOT NULL,
  `comments` varchar(128) DEFAULT NULL COMMENT '备注',
  `params` varchar(128) DEFAULT NULL COMMENT '参数名',
  `action_group` varchar(64) DEFAULT NULL COMMENT '分组',
  PRIMARY KEY (`id`),
  KEY `idx_action_mapping_key` (`action_key`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COMMENT='访问映射';

