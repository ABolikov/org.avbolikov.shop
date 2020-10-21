alter table role
    drop index role_name_index;
GO

alter table user_role
    drop foreign key user_role_role_id_fk;
GO

alter table user_role
    drop foreign key user_role_user_id_fk;
GO

drop table role;
GO

drop table user;
GO

drop table user_role;
GO
