CREATE TABLE `p_fqa` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ask` varchar(128) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '问题',
  `question` varchar(1024) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '答案',
  `author` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '作者',
  `ask_type_code` varchar(32) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '问题分类编码(使用acooly-component-treetype组件，scheme: acooly-portlets-fqa)',
  `ask_type_name` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '问题分类名称(冗余)',
  `hits` int(11) DEFAULT '0' COMMENT '访问量',
  `status` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '状态 {enable:可用,disable:禁用}',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  `comments` varchar(45) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `p_fqa_body` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `body` text COLLATE utf8mb4_unicode_ci COMMENT '答案内容',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='FQA答案详情';
