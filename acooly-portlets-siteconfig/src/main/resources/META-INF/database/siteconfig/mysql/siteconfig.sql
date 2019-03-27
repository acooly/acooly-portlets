CREATE TABLE `portlet_site_config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(45) NOT NULL DEFAULT 'def' COMMENT '类型 {def:默认}',
  `title` varchar(45) NOT NULL COMMENT '标题',
  `name` varchar(45) NOT NULL COMMENT '参数键',
  `value` varchar(4000) DEFAULT NULL COMMENT '参数值',
  `comments` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_SITE_CONFIG` (`type`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

INSERT INTO `portlet_site_config` (`id`, `type`, `title`, `name`, `value`, `comments`, `create_time`, `update_time`)
VALUES
	(1, 'def', '客服热线', 'serviceTel', '023123456', '', '2017-05-15 03:41:00', NULL),
	(2, 'def', '服务时间', 'serviceWorkHours', '09:00 - 18:00', '', '2017-05-15 03:42:14', NULL),
	(3, 'def', '服务邮箱', 'serviceEmail', 'pu.zhang@foxmail.com', '', '2017-05-15 03:42:45', NULL),
	(4, 'def', '服务微博', 'serviceWeibo', 'aaaaaaaaa', '', '2017-05-15 03:43:11', NULL),
	(5, 'def', '服务QQ', 'serviceQQ', '15366632', '', '2017-05-15 03:43:34', NULL),
	(6, 'def', '服务QQ群', 'serviceQQGroup', '15366632', '', '2017-05-15 03:43:54', NULL),
	(7, 'def', '微信公众号', 'serviceWeChat', 'zp820705', '', '2017-05-15 03:44:38', '2017-05-15 03:55:14');

