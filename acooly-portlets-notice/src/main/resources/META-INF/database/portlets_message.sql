CREATE TABLE `p_message_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `title` varchar(64) DEFAULT NULL COMMENT '消息标题',
  `content` varchar(1024) DEFAULT NULL COMMENT '消息内容',
  `content_type` varchar(16) DEFAULT NULL COMMENT '内容类型 {text:信息,data:数据}',
  `context` varchar(255) DEFAULT NULL COMMENT '会话（附带的自定义结构体）',
  `scheduler_time` datetime DEFAULT NULL COMMENT '定时发送时间(保留)',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `sender` varchar(32) DEFAULT NULL COMMENT '发送人',
  `status` varchar(16) DEFAULT NULL COMMENT '状态',
  `type` varchar(255) DEFAULT NULL COMMENT '消息类型{broadcast:广播,group:群发}',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `comments` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;