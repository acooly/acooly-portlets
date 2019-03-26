CREATE TABLE `p_action_analysis_visits` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `action_day` date NOT NULL COMMENT '统计日期',
  `type` varchar(16) NOT NULL DEFAULT '' COMMENT '类型{PV:点击量,UV:用户量,IP:独立IP数}',
  `visits` int(11) NOT NULL DEFAULT '0' COMMENT '访问量',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `action_day` (`action_day`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='访问量统计';

