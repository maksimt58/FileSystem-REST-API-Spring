insert into users (first_name, last_name, username, password, role_id, status) values ('Maks', 'Tata', 'maks.tata', 'password', 1, 'NEW');
insert into users (first_name, last_name, username, password, role_id, status) values ('John', 'Dou', 'john.dou', 'password', 3, 'NEW');
insert into users (first_name, last_name, username, password, role_id, status) values ('Eugene', 'Proselyte', 'eugene.proselyte', 'password', 2, 'NEW');

insert into files (file_name, file_path, status) values ('picture', 'C:\\Program Files', 'NEW');
insert into files (file_name, file_path, status) values ('film', 'C:\\Program Files x86', 'NEW');

insert into events (event_name, file_id, user_id, status) values ('UPLOAD', 1, 1, 'NEW');
insert into events (event_name, file_id, user_id, status) values ('DOWNLOAD', 2, 3, 'NEW');

insert into roles (role_name) values ('ROLE_ADMIN');
insert into roles (role_name) values ('ROLE_MODERATOR');
insert into roles (role_name) values ('ROLE_USER');
