INSERT INTO `user` (`name`, `password`)
    VALUE ('admin', '$2a$10$uedJ6jkBS08x5mxZY6gV6.LAKSd202CiVutxz5VDq3TIyj9alkmIq'),
    ('guest', '$2a$10$uedJ6jkBS08x5mxZY6gV6.LAKSd202CiVutxz5VDq3TIyj9alkmIq');
GO

INSERT INTO `role` (`name`)
    VALUE ('ROLE_ADMIN'), ('ROLE_GUEST');
GO

INSERT INTO `user_role`(`user_id`, `role_id`)
SELECT (SELECT id FROM `user` WHERE `name` = 'admin'), (SELECT id FROM `role` WHERE `name` = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT id FROM `user` WHERE `name` = 'guest'), (SELECT id FROM `role` WHERE `name` = 'ROLE_GUEST');
GO
