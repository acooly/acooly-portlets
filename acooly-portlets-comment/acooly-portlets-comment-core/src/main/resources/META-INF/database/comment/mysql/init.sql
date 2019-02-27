INSERT INTO `sys_resource` (`ID`, `PARENTID`, `NAME`, `TYPE`, `SHOW_STATE`, `ORDER_TIME`, `VALUE`, `SHOW_MODE`, `ICON`, `DESCN`)
VALUES('20190102',NULL ,'评论组件', 'MENU', '0', '2017-12-13 15:02:00', NULL, '1', 'fa fa-commenting', NULL);
INSERT INTO `sys_resource` (`ID`, `PARENTID`, `NAME`, `TYPE`, `SHOW_STATE`, `ORDER_TIME`, `VALUE`, `SHOW_MODE`, `ICON`, `DESCN`)
VALUES('20190106', '20190102','评论记录', 'URL', '0', '2017-12-13 15:02:00', '/manage/portlets/comment/comment/index.html', '1','fa fa-commenting', NULL);
INSERT INTO `sys_resource` (`ID`, `PARENTID`, `NAME`, `TYPE`, `SHOW_STATE`, `ORDER_TIME`, `VALUE`, `SHOW_MODE`, `ICON`, `DESCN`, `create_time`, `update_time`)
VALUES(`20190107`, '20190102', '评论动作', 'URL', 0, '2019-01-09 18:34:55', '/manage/portlets/comment/commentLog/index.html', 1,'fa fa-circle-o',
NULL, '2019-01-09 18:34:55', '2019-01-10 16:16:43');

INSERT INTO `sys_role_resc` (`ROLE_ID`, `RESC_ID`) VALUES ('1', '20190102');
INSERT INTO `sys_role_resc` (`ROLE_ID`, `RESC_ID`) VALUES ('1', '20190106');
INSERT INTO `sys_role_resc` (`ROLE_ID`, `RESC_ID`) VALUES ('1', '20190107');