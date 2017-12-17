INSERT INTO `sys_resource` (`ID`, `PARENTID`, `NAME`, `TYPE`, `SHOW_STATE`, `ORDER_TIME`, `VALUE`, `SHOW_MODE`, `ICON`, `DESCN`) VALUES ('171213', NULL ,'消息通知', 'MENU', '0', '2017-12-13 15:02:00', NULL, '1', 'icons-resource-fangdaozhuomian', NULL);
INSERT INTO `sys_resource` (`ID`, `PARENTID`, `NAME`, `TYPE`, `SHOW_STATE`, `ORDER_TIME`, `VALUE`, `SHOW_MODE`, `ICON`, `DESCN`) VALUES ('17121301', '171213','消息通知', 'URL', '0', '2017-12-13 15:02:00', '/manage/portlets/notice/noticeInfo/index.html', '1', 'icons-resource-fangdaozhuomian', NULL);

INSERT INTO `sys_role_resc` (`ROLE_ID`, `RESC_ID`) VALUES ('1', '171213');
INSERT INTO `sys_role_resc` (`ROLE_ID`, `RESC_ID`) VALUES ('1', '17121301');