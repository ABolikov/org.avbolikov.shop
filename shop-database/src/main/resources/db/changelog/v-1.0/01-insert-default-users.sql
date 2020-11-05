INSERT INTO `user` (`name`, `password`)
    VALUE ('admin', '$2y$12$ltdVoSeQJ89LvPPJgCnkcuieThLM1yLs2ngmGpwTxPJtA.uELeJN6'),
    ('guest', '$2y$12$X5OixROKQLotXn7R8sd2wenQ9/PtEeSvgOcMXy7bFVGa.Hio9r/Vm');
GO

INSERT INTO `role` (`name`)
    VALUE ('ROLE_ADMIN'), ('ROLE_GUEST');
GO

INSERT INTO `user_role`(`user_id`, `role_id`)
SELECT (SELECT id FROM `user` WHERE `name` = 'admin'), (SELECT id FROM `role` WHERE `name` = 'ROLE_ADMIN')
UNION ALL
SELECT (SELECT id FROM `user` WHERE `name` = 'guest'), (SELECT id FROM `role` WHERE `name` = 'ROLE_GUEST');
GO