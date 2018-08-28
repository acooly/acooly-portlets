CREATE TABLE `p_notice_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(64) DEFAULT NULL COMMENT '消息标题',
  `content` varchar(1024) NOT NULL DEFAULT '' COMMENT '消息内容',
  `content_type` varchar(16) NOT NULL DEFAULT '' COMMENT '内容类型 {text:信息,data:数据}',
  `context` varchar(1024) DEFAULT NULL COMMENT '会话（附带的自定义结构体）',
  `scheduler_time` datetime DEFAULT NULL COMMENT '定时发送时间(保留)',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `sender` varchar(32) NOT NULL DEFAULT '' COMMENT '发送人',
  `receiver` varchar(32) NOT NULL COMMENT '接收人 广播的话接收人为ALL',
  `send_total` int(11) DEFAULT NULL COMMENT '发送总数',
  `received_total` int(11) DEFAULT NULL COMMENT '已读总数',
  `status` varchar(16) DEFAULT NULL COMMENT '状态{apply:提交,sending:发送中,finish:完成}',
  `type` varchar(16) NOT NULL DEFAULT '' COMMENT '消息类型{broadcast:广播,group:群发}',
  `custom_group` varchar(32) DEFAULT NULL COMMENT '上层业务自定义消息分组',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `comments` varchar(255) DEFAULT NULL COMMENT '备注',
  `readed` tinyint(1) DEFAULT '0' COMMENT '该消息是否已读',
  `push` tinyint(1) DEFAULT '0' COMMENT '是否需要推送app',
  `push_no` varchar(32) DEFAULT NULL COMMENT '推送流水号 每次推送唯一',
  PRIMARY KEY (`id`),
  KEY `idx_custom_group` (`custom_group`),
  KEY `idx_receiver` (`receiver`),
  KEY `idx_push_no` (`push_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告消息';

CREATE TABLE `p_notice_read` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `receiver` varchar(32) NOT NULL DEFAULT '' COMMENT '接收人',
  `broadcast_read` varchar(2048) DEFAULT NULL COMMENT '广播已读(逗号分隔)',
  `status` varchar(16) DEFAULT NULL COMMENT '状态{enable:有效,disable:无效}',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `comments` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `receiver_UNIQUE` (`receiver`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公告消息读取状态';


