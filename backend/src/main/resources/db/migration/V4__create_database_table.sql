create table if not exists patient_request
(
    id                       int auto_increment
        primary key,
    number_order             int           null,
    patient_number           varchar(10)   null,
    medicine_code            varchar(15)   null,
    patient_name             varchar(255)  null,
    department               varchar(10)   null,
    save_number              int default 0 null,
    in_date                  date          null,
    out_date                 date          null,
    reception_date           datetime      null,
    appointment_patient_date date          null,
    copy_quantity            int default 1 null,
    copy_cost                int           null,
    note                     mediumtext    null,
    done_patient_date        datetime      null,
    sign_date                datetime      null,
    state                    int default 0 null,
    status                   int default 1 null,
    is_delivery              int default 0 null,
    delivery_order_number    int           null,
    delivery_year_of_order   int           null,
    delivery_address         varchar(4000) null,
    delivery_phone           varchar(15)   null,
    id_province              int           null,
    id_district              int           null,
    delivery_cost            int           null,
    delivery_date            datetime      null,
    created_at               datetime      null,
    created_by               int           null,
    updated_at               datetime      null,
    updated_by               int           null
);

create table if not exists approvals
(
    id                 int auto_increment
        primary key,
    id_patient_request int                                   not null,
    id_user            int                                   not null,
    role               varchar(255)                          null,
    approval_date      datetime                              null,
    status             tinyint   default 0                   null,
    comment            varchar(4000)                         null,
    created_at         timestamp default current_timestamp() null,
    created_by         bigint                                null,
    updated_at         timestamp default current_timestamp() null,
    updated_by         bigint                                null,
    constraint fk_approvals_id_patient_request
        foreign key (id_patient_request) references patient_request (id)
);

create table if not exists assignments
(
    id                 int auto_increment
        primary key,
    id_patient_request int                                   null,
    id_copy_user       int                                   null,
    appointment_date   date                                  null,
    completion_date    datetime                              null,
    status             tinyint   default 1                   null,
    created_at         timestamp default current_timestamp() null,
    created_by         bigint                                null,
    updated_at         timestamp default current_timestamp() null,
    updated_by         bigint                                null,
    constraint fk_assignments_id_patient_request
        foreign key (id_patient_request) references patient_request (id)
);

create table if not exists roles
(
    id           int auto_increment
        primary key,
    name         varchar(40)                           null,
    descriptions varchar(255)                          null,
    status       tinyint   default 1                   null,
    created_at   timestamp default current_timestamp() null,
    created_by   bigint                                null,
    updated_at   timestamp default current_timestamp() null,
    updated_by   bigint                                null
);

create table if not exists states
(
    id           int auto_increment
        primary key,
    name         varchar(40)                           null,
    descriptions varchar(255)                          null,
    status       tinyint   default 1                   null,
    created_at   timestamp default current_timestamp() null,
    created_by   bigint                                null,
    updated_at   timestamp default current_timestamp() null,
    updated_by   bigint                                null
);

create table if not exists state_history
(
    id                 int auto_increment
        primary key,
    id_patient_request int                                   null,
    state              tinyint                               null,
    created_at         timestamp default current_timestamp() null,
    created_by         bigint                                null,
    updated_at         timestamp default current_timestamp() null,
    updated_by         bigint                                null,
    constraint fk_state_history_id_patient_request
        foreign key (id_patient_request) references patient_request (id)
);



