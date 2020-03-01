INSERT INTO `sys_resource` (`id`, `parentid`, `name`, `type`, `show_state`, `order_time`, `value`, `show_mode`, `icon`, `descn`, `create_time`, `update_time`)
VALUES
	(202002291422, NULL, 'FQA', 'MENU', 0, '2020-02-26 12:45:23', '', 1, 'fa-question-circle', NULL, '2020-02-26 12:45:23', '2020-02-26 12:45:23'),
	(202002291423, 202002291422, '常见问题', 'URL', 0, '2020-02-26 12:45:57', '/manage/portlets/fqa/fqa/index.html', 1, 'fa-circle-o', NULL, '2020-02-26 12:45:20', '2020-02-26 12:45:57'),
	(202002291424, 202002291422, '问题分类', 'URL', 0, '2020-02-26 12:47:45', '/manage/module/treeType/treeType/index.html?theme=FQA', 1, 'fa-circle-o', NULL, '2020-02-26 12:47:45', '2020-02-26 12:47:45');

INSERT INTO `sys_role_resc` (`role_id`, `resc_id`)
VALUES
	(1, 202002291422),
	(1, 202002291423),
	(1, 202002291424);
