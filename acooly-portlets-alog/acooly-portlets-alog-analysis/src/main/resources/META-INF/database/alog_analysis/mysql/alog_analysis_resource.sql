INSERT INTO `sys_resource` (`ID`, `PARENTID`, `NAME`, `TYPE`, `SHOW_STATE`, `ORDER_TIME`, `VALUE`, `SHOW_MODE`, `ICON`, `DESCN`, `create_time`, `update_time`)
VALUES
	(20190325, 20190322, '行为分析', 'MENU', 0, '2019-03-24 13:25:57', '', 1, 'fa-bar-chart', NULL, '2019-03-24 13:25:57', '2019-03-24 13:25:57'),
	(201903251, 20190325, '访问量分析', 'URL', 0, '2019-03-24 13:49:20', '/manage/alog/analysis/visits.html', 1, 'fa-circle-o', NULL, '2019-03-24 13:49:20', '2019-03-24 16:46:37'),
	(201903252, 20190325, '今日访问量', 'URL', 0, '2019-03-30 17:34:02', '/manage/alog/analysis/widget/visits.html', 1, 'fa-circle-o', NULL, '2019-03-30 17:34:02', '2019-03-31 01:17:45');

INSERT INTO `sys_role_resc` (`ROLE_ID`, `RESC_ID`)
VALUES
	(1, 20190325),
	(1, 201903251),
	(1, 201903252);

