CREATE TABLE `p_success_story` (
   `ID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
   `title` varchar(45) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '案例名称',
   `discn` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '案例简介',
   `type` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '案例类型',
   `industry` varchar(16) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '行业',
   `online_date` DATE COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上线日期',
   `logo` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '{title:’图标’,type:’file’}',
   `website` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '{title:’网址’,type:’url’}',
   `sort_time` bigint(20) DEFAULT NULL COMMENT '排序值',
   `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
   `comments` varchar(128) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
   PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
