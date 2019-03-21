INSERT INTO `sys_resource` (`ID`, `PARENTID`, `NAME`, `TYPE`, `SHOW_STATE`, `ORDER_TIME`, `VALUE`, `SHOW_MODE`, `ICON`, `DESCN`, `create_time`, `update_time`)
VALUES
	(20190322, NULL, '行为日志', 'MENU', 0, '2019-03-21 13:20:29', '', 1, 'fa-film', NULL, '2019-03-21 13:20:29', '2019-03-21 13:20:29'),
	(201903221, 20190322, '访问日志', 'URL', 0, '2019-03-21 13:21:02', '/manage/portlets/alog/actionLog/index.html', 1, 'fa-circle-o', NULL, '2019-03-21 13:21:02', '2019-03-21 13:21:02'),
	(201903222, 20190322, '埋点管理', 'URL', 0, '2019-03-21 13:21:31', '/manage/portlets/alog/actionMapping/index.html', 1, 'fa-circle-o', NULL, '2019-03-21 13:21:31', '2019-03-21 13:21:31');

INSERT INTO `sys_role_resc` (`ROLE_ID`, `RESC_ID`)
VALUES
	(1, 20190322),
	(1, 201903221),
	(1, 201903222);
