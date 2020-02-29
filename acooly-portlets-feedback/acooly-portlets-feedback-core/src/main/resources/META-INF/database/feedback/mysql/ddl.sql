DROP TABLE IF EXISTS `p_feedback`;
CREATE TABLE `p_feedback` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` varchar(16) NOT NULL COMMENT '类型 {suggest:建议,complaint:投诉,consult:咨询}',
  `title` varchar(128) DEFAULT NULL COMMENT '标题',
  `content` varchar(512) NOT NULL COMMENT '内容',
  `user_key` varchar(64) DEFAULT NULL COMMENT '用户标志',
  `user_name` varchar(32) DEFAULT NULL COMMENT '用户名(冗余)',
  `busi_code` varchar(45) DEFAULT NULL COMMENT '业务分类编码',
  `busi_name` varchar(45) DEFAULT NULL COMMENT '业务分类名称',
  `user_ip` varchar(32) DEFAULT NULL COMMENT '用户IP',
  `phone_no` varchar(21) DEFAULT NULL COMMENT '联系电话',
  `mail` varchar(255) DEFAULT NULL COMMENT '联系邮箱',
  `address` varchar(128) DEFAULT NULL COMMENT '联系地址',
  `contact_info` varchar(255) DEFAULT NULL COMMENT '联系信息',
  `reply_content` varchar(512) DEFAULT NULL COMMENT '回复内容',
  `reply_user` varchar(32) DEFAULT NULL COMMENT '回复人',
  `status` varchar(16) DEFAULT NULL COMMENT '状态 {apply:提交,processing:处理中,complete:处理完成}',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `comments` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户反馈';
