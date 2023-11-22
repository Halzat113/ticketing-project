insert into roles(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, description)
VALUES ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Admin'),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Manager'),
       ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, 'Employee');


insert into users(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, enabled,
                  firstname, gender, lastname, user_name, role_id)
values ('2022-01-05 00:00:00', 1, false, '2022-01-05 00:00:00', 1, true, 'admin', 'MALE', 'admin', 'admin@admin.com',
        2);

insert into users(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, enabled,
                  firstname, gender, lastname, user_name, role_id)
values ('2022-02-05 00:00:00', 1, false, '2022-02-05 00:00:00', 1, true, 'John', 'MALE', 'Doe', 'John@doe.com',
        3);

insert into projects(insert_date_time, insert_user_id, is_deleted, last_update_date_time, last_update_user_id, project_name,
                  project_code, start_date, end_date, project_status, project_detail,manager_id )
values ('2022-02-05 00:00:00', 1, false, '2022-02-05 00:00:00', 1, 'Spring Review', 'PR001', '2022-01-05 00:00:00', '2022-02-05 00:00:00', 'Open',
        'detail',1);
