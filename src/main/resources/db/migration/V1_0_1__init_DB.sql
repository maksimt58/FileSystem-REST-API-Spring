CREATE TABLE users
(
    id INT NOT NULL auto_increment PRIMARY KEY,
    first_name varchar(255) NOT NULL,
    last_name  varchar(255) NOT NULL,
    username varchar(255) NOT NULL,
    password varchar(255) NOT NULL,
    role_id INT NOT NULL,
    status varchar(25) NOT NULL
);

CREATE TABLE files
(
    id INT NOT NULL auto_increment PRIMARY KEY,
    file_name varchar(255) NOT NULL,
    file_path varchar(255) NOT NULL,
    status varchar(25) NOT NULL
);

CREATE TABLE events
(
    id INT NOT NULL auto_increment PRIMARY KEY,
    event_name varchar(255) NOT NULL,
    file_id INT NOT NULL,
    user_id INT NOT NULL,
    status varchar(25) NOT NULL,
    foreign key (file_id) references files (id) ON DELETE CASCADE on update cascade,
    foreign key (user_id) references users (id) ON DELETE CASCADE on update cascade
);

CREATE TABLE roles
(
    id INT NOT NULL auto_increment PRIMARY KEY,
    role_name varchar(255) NOT NULL
);
