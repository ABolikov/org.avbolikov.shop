create table role
(
    id   int       not null auto_increment,
    name varchar(255) not null,
    primary key (id)
) engine = InnoDB;
GO

create table user
(
    id       int       not null auto_increment,
    name     varchar(32)  not null,
    password varchar(128) not null,
    age      integer,
    email    varchar(255),
    primary key (id)
) engine = InnoDB;
GO

create table user_role
(
    user_id int not null,
    role_id int not null,
    primary key (user_id, role_id)
) engine = InnoDB;
GO

alter table role
    add constraint role_name_index unique (name);
GO

alter table user_role
    add constraint user_role_role_id_fk
        foreign key (role_id)
            references role (id);
GO

alter table user_role
    add constraint user_role_user_id_fk
        foreign key (user_id)
            references user (id);
GO