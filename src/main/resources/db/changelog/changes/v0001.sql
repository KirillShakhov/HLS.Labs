create table categories
(
    id   bigserial       not null
        primary key,
    name varchar(255) not null
);

create table products
(
    id          bigserial       not null
        primary key,
    description varchar(255),
    name        varchar(255) not null,
    username    varchar(255),
    category_id bigint       not null
);

create table orders
(
    id            bigserial not null
        primary key,
    delivery_info varchar(255),
    payment_id    varchar(255),
    username      varchar(255),
    product_id    bigint not null
);

create table product_attachments
(
    product_id    bigint not null,
    attachment_id bigint
);

CREATE SEQUENCE hibernate_sequence;
