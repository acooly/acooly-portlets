CREATE TABLE `p_comment` (
  `id` bigint(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `comment_no` varchar(64) DEFAULT NULL COMMENT '评论编码',
  `parent_id` bigint(11) DEFAULT NULL COMMENT '父ID(顶层为null)',
  `path` varchar(128) DEFAULT NULL,
  `busi_type` varchar(32) NOT NULL COMMENT '业务类型(标记是什么业务)',
  `busi_key` varchar(32) NOT NULL COMMENT '业务标志(标记业务的ID或key)',
  `user_no` varchar(64) NOT NULL COMMENT '评论人编号',
  `user_name` varchar(45) DEFAULT NULL COMMENT '评论人用户名',
  `profile_photo` varchar(128) DEFAULT NULL,
  `user_ip` varchar(64) DEFAULT NULL,
  `title` varchar(45) DEFAULT NULL COMMENT '标题',
  `content` varchar(1024) NOT NULL COMMENT '评论内容',
  `origin_content` varchar(1024) DEFAULT NULL,
  `thumbsup` int(11) DEFAULT NULL COMMENT '点赞数',
  `repeats` int(11) DEFAULT '0' COMMENT '复评数',
  `star` int(11) DEFAULT '0' COMMENT '星级1-5',
  `attach_count` int(11) DEFAULT '0' COMMENT '附件数量',
  `sticky` varchar(32) DEFAULT NULL COMMENT '是否置顶{yes:是,no:否}',
  `status` varchar(32) DEFAULT NULL COMMENT '状态{enable_publish:发布,enable_report:举报,enable_review:管理编辑,disabled:禁用}',
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `comments` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `p_comment_no` (`comment_no`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4;

CREATE TABLE `p_comment_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `comment_id` bigint(20) NOT NULL COMMENT '所属评论',
  `user_no` varchar(64) DEFAULT NULL COMMENT '用户标志',
  `user_name` varchar(45) DEFAULT NULL COMMENT '用户名称',
  `profile_photo` varchar(255) DEFAULT NULL COMMENT '头像',
  `action_type` varchar(45) NOT NULL COMMENT '操作类型{举报:report,thumbsup:点赞}',
  `user_ip` varchar(45) DEFAULT NULL COMMENT '操作IP',
  `action_content` varchar(512) DEFAULT NULL COMMENT '相关内容',
  `status` varchar(32) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `comments` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='评论日志';

CREATE TABLE `p_comment_attach` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `comment_id` bigint(20) NOT NULL COMMENT '评论ID',
  `comment_no` varchar(64) NOT NULL COMMENT '评论编码',
  `attach_type` varchar(32) NOT NULL COMMENT '附件类型 {photo:图片,video:视频,other:其他}',
  `thumbnail` varchar(128) DEFAULT NULL COMMENT '缩略图',
  `attach_path` varchar(128) NOT NULL COMMENT '附件地址',
  `sort_time` bigint(20) DEFAULT NULL COMMENT '排序值',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `comments` varchar(128) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='评论附件';
