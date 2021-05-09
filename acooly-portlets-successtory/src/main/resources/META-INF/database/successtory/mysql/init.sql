INSERT INTO `sys_resource` (`id`, `parentid`, `name`, `type`, `show_state`, `order_time`, `value`, `show_mode`, `icon`, `descn`, `create_time`, `update_time`)
VALUES
(202105090010, NULL, '成功案例', 'MENU', 0, '2021-05-08 18:31:56', '', 1, 'fa-home', NULL, '2021-05-08 18:31:56', '2021-05-08 18:31:56'),
(202105090011, 202105090010, '成功案例', 'URL', 0, '2021-05-08 18:32:29', '/manage/portlets/successtory/successStory/index.html', 1, 'fa-circle-o', NULL, '2021-05-08 18:32:29', '2021-05-08 18:49:45');

INSERT INTO `sys_role_resc` (`ROLE_ID`, `RESC_ID`) VALUES ('1', '202105090010');
INSERT INTO `sys_role_resc` (`ROLE_ID`, `RESC_ID`) VALUES ('1', '202105090011');