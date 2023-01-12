create table users
(
    id         uuid         not null primary key,
    email      varchar(255) not null unique,
    first_name varchar(255) not null,
    last_name  varchar(255),
    pass       varchar(255) not null,
    username   varchar(255) not null unique,
    role       varchar(255) not null
);