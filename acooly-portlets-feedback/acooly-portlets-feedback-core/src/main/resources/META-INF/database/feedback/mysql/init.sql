INSERT INTO `sys_resource` (`ID`, `PARENTID`, `NAME`, `TYPE`, `SHOW_STATE`, `ORDER_TIME`, `VALUE`, `SHOW_MODE`, `ICON`, `DESCN`)
VALUES ('20190101',NULL ,'反馈组件', 'MENU', '0', '2017-12-13 15:02:00', NULL, '1', 'fa fa-comments', NULL);
INSERT INTO `sys_resource`(`ID`, `PARENTID`, `NAME`, `TYPE`, `SHOW_STATE`, `ORDER_TIME`, `VALUE`, `SHOW_MODE`, `ICON`, `DESCN`, `create_time`, `update_time`)
VALUES (2019011501, 20190101, '反馈管理', 'URL', 0, '2019-01-14 22:24:33', '/manage/portlets/feedback/feedback/index.html', 1, 'fa fa-comments', NULL,'2019-01-14 22:24:33', '2019-01-14 22:24:33');
INSERT INTO `sys_resource` (`id`, `parentid`, `name`, `type`, `show_state`, `order_time`, `value`, `show_mode`, `icon`, `descn`, `create_time`, `update_time`)
VALUES (2019011502, 20190101, '反馈业务分类', 'URL', 0, '2020-02-29 07:46:41', '/manage/module/treeType/treeType/index.html?theme=feedback', 1, 'fa-circle-o', NULL, '2020-02-29 07:46:41', '2020-02-29 13:12:06');

INSERT INTO `sys_role_resc` (`ROLE_ID`, `RESC_ID`) VALUES ('1', '20190101');
INSERT INTO `sys_role_resc` (`ROLE_ID`, `RESC_ID`) VALUES ('1', '2019011501');
INSERT INTO `sys_role_resc` (`ROLE_ID`, `RESC_ID`) VALUES ('1', '2019011502');