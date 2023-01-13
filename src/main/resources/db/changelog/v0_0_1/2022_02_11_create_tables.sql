create table payments
(
    id          bigint       not null
        primary key,
    create_date varchar(255) not null,
    description varchar(255),
    token       varchar(255) not null
);