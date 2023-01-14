create table chats
(
    id    bigserial not null primary key,
    admin varchar(255),
    title varchar(255)
);

create table messages
(
    id       bigserial not null primary key,
    date     varchar(255),
    message  varchar(255),
    username varchar(255)
);

create table chat_messages
(
    chat_id    bigint not null,
    message_id bigint not null unique
);

create table chat_users
(
    chat_id bigint not null,
    tag     varchar(255)
);

CREATE SEQUENCE hibernate_sequence;