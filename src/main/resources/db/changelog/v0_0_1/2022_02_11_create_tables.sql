create table attachment
(
    id          bigserial primary key,
    base64      text,
    type        text,
    create_date text
);
