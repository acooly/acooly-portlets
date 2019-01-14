INSERT INTO `sys_resource` (`ID`, `PARENTID`, `NAME`, `TYPE`, `SHOW_STATE`, `ORDER_TIME`, `VALUE`, `SHOW_MODE`, `ICON`, `DESCN`) VALUES ('20190101',NULL ,'前端组件', 'MENU', '0', '2017-12-13 15:02:00', NULL, '1', 'icons-resource-fangdaozhuomian', NULL);
INSERT INTO `sys_resource`(`ID`, `PARENTID`, `NAME`, `TYPE`, `SHOW_STATE`, `ORDER_TIME`, `VALUE`, `SHOW_MODE`, `ICON`, `DESCN`, `create_time`, `update_time`) VALUES (2019011501, 20190101, '反馈组件', 'URL', 0, '2019-01-14 22:24:33', '/manage/portlets/feedback/feedback/index.html', 1, 'icons-resource-admin', NULL, '2019-01-14 22:24:33', '2019-01-14 22:24:33');


INSERT INTO `sys_role_resc` (`ROLE_ID`, `RESC_ID`) VALUES ('1', '20190101');
INSERT INTO `sys_role_resc` (`ROLE_ID`, `RESC_ID`) VALUES ('1', '2019011501');