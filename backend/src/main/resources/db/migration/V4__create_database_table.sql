create table if not exists patient_request
(
    id                       int auto_increment primary key,
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
    id                 int auto_increment                    primary key,
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
    id                 int auto_increment                    primary key,
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
    id           int auto_increment                    primary key,
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
    id           int auto_increment                    primary key,
    code         tinyint   default 0                   null,
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
    id                 int auto_increment                    primary key,
    id_patient_request int                                   null,
    state              tinyint                               null,
    created_at         timestamp default current_timestamp() null,
    created_by         bigint                                null,
    updated_at         timestamp default current_timestamp() null,
    updated_by         bigint                                null,
    constraint fk_state_history_id_patient_request
        foreign key (id_patient_request) references patient_request (id)
);

INSERT INTO `roles` (`id`, `name`, `descriptions`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 'ADMIN', 'Chủ sở hữu phần mềm', 1, '2024-08-20 12:45:30', NULL, '2024-08-20 12:45:30', NULL);
INSERT INTO `roles` (`id`, `name`, `descriptions`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 'SECRETARY', 'Thư ký', 1, '2024-08-20 12:45:42', NULL, '2024-08-20 12:45:42', NULL);
INSERT INTO `roles` (`id`, `name`, `descriptions`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (3, 'COPY_MAN', 'Người sao chép', 1, '2024-08-20 12:45:56', NULL, '2024-08-20 12:45:56', NULL);
INSERT INTO `roles` (`id`, `name`, `descriptions`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (4, 'APPROVER', 'Người xác nhận', 1, '2024-08-20 12:46:09', NULL, '2024-08-20 12:46:09', NULL);
INSERT INTO `roles` (`id`, `name`, `descriptions`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (5, 'LEADER', 'Lãnh đạo', 1, '2024-08-20 12:46:21', NULL, '2024-08-20 12:46:21', NULL);
INSERT INTO `roles` (`id`, `name`, `descriptions`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (6, 'DATA_ENTRY_PERSON', 'Người tiếp đón', 1, '2024-08-20 12:46:33', NULL, '2024-08-20 12:46:33', NULL);
INSERT INTO `roles` (`id`, `name`, `descriptions`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (7, 'MANAGER', 'Người quản lý', 1, '2024-08-20 12:46:44', NULL, '2024-08-20 12:46:44', NULL);


INSERT INTO `states` (`id`, `code`, `name`, `descriptions`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (1, 0, 'PENDING', 'Chưa xử lý', 1, '2024-08-20 12:50:24', NULL, '2024-08-20 12:50:24', NULL);
INSERT INTO `states` (`id`, `code`, `name`, `descriptions`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (2, 1, 'ASSIGNED', 'Đã phân công', 1, '2024-08-20 12:50:37', NULL, '2024-08-20 12:50:37', NULL);
INSERT INTO `states` (`id`, `code`, `name`, `descriptions`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (3, 2, 'IN_PROGRESS', 'Đang tiến hành', 1, '2024-08-20 12:50:47', NULL, '2024-08-20 12:50:47', NULL);
INSERT INTO `states` (`id`, `code`, `name`, `descriptions`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (4, 3, 'MANAGER_APPROVED', 'Quản lý đã phê duyệt', 1, '2024-08-20 12:50:55', NULL, '2024-08-20 12:50:55', NULL);
INSERT INTO `states` (`id`, `code`, `name`, `descriptions`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (5, 4, 'DIRECTOR_APPROVED', 'Thủ trưởng đã phê duyệt', 1, '2024-08-20 12:51:04', NULL, '2024-08-20 12:51:04', NULL);
INSERT INTO `states` (`id`, `code`, `name`, `descriptions`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (6, 5, 'REJECTED', 'Đã từ chối', 1, '2024-08-20 12:51:14', NULL, '2024-08-20 12:51:14', NULL);
INSERT INTO `states` (`id`, `code`, `name`, `descriptions`, `status`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES (7, 6, 'COMPLETE', 'Hoàn thành', 1, '2024-08-20 12:51:48', NULL, '2024-08-20 12:51:48', NULL);


