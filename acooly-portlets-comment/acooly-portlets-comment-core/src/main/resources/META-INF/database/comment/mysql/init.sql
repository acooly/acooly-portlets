INSERT INTO `sys_resource` (`ID`, `PARENTID`, `NAME`, `TYPE`, `SHOW_STATE`, `ORDER_TIME`, `VALUE`, `SHOW_MODE`, `ICON`, `DESCN`) VALUES ('20190101',NULL ,'前端组件', 'MENU', '0', '2017-12-13 15:02:00', NULL, '1', 'icons-resource-fangdaozhuomian', NULL);
INSERT INTO `sys_resource` (`ID`, `PARENTID`, `NAME`, `TYPE`, `SHOW_STATE`, `ORDER_TIME`, `VALUE`, `SHOW_MODE`, `ICON`, `DESCN`) VALUES('20190106', '20190101','评论组件', 'URL', '0', '2017-12-13 15:02:00', '/manage/portlets/comment/comment/index.html', '1','icons-resource-fangdaozhuomian', NULL);

INSERT INTO `sys_role_resc` (`ROLE_ID`, `RESC_ID`) VALUES ('1', '20190101');
INSERT INTO `sys_role_resc` (`ROLE_ID`, `RESC_ID`) VALUES ('1', '20190106');