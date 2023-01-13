create table messages
(
    id       bigserial primary key,
    username text,
    message  text,
    date     date
);

create table chats
(
    id    bigserial primary key,
    title text not null,
    admin text not null,
    users text
);

