INSERT INTO `user` (`name`, `password`)
    VALUE ('admin', '$2y$12$46cr8yUIKCk044aKs.5.WeGPCiRLXNVaf47Oin7H/fDWpUCr7AUzm'),
    ('guest', '$2y$12$DUWxb8vOIHJxCKgIDh3KzugHLL/lKa56iOq4H1/UIQ8kRT/jD4NY6');
GO

INSERT INTO `role` (`name`)
    VALUE ('ADMIN'), ('GUEST');
GO

INSERT INTO `user_role`(`user_id`, `role_id`)
SELECT (SELECT id FROM `user` WHERE `name` = 'admin'), (SELECT id FROM `role` WHERE `name` = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT id FROM `user` WHERE `name` = 'guest'), (SELECT id FROM `role` WHERE `name` = 'ROLE_GUEST');
GO
