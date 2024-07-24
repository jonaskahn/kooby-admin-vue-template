create or replace table users
(
    id                 bigint auto_increment,
    username           varchar(128)                          not null,
    preferred_username bigint                                not null,
    email              varchar(128)                          not null,
    full_name          varchar(255)                          null,
    password           varchar(80)                           not null,
    status             tinyint   default 1                   not null,
    roles              json                                  null,
    created_at         timestamp default current_timestamp() null,
    created_by         bigint    default 0                   null,
    updated_at         timestamp default current_timestamp() null,
    updated_by         bigint    default 0                   null,
    constraint `PRIMARY`
        primary key (id),
    constraint users_email_uk
        unique (email),
    constraint users_preferred_username_uk
        unique (preferred_username),
    constraint users_username_key_uk
        unique (username)
);

INSERT INTO users
    (id,
     username,
     preferred_username,
     email,
     full_name,
     password,
     status,
     roles)
VALUES
    ( 1,
      'admin@localhost',
      602049386575465522,
      'admin@localhost',
      'admin',
      '$2a$12$xIwOx5Yh/V0TtWcq/.VROunfYIQz2hpiEien6u7IrJvqeiGOX7yeC',
      1,
      '["app:user", "app:admin"]'
    );

ALTER TABLE users AUTO_INCREMENT = 1000;


