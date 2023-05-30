create table if not exists sites (
        id serial primary key NOT NULL,
        login  varchar(2000) unique,
        password  varchar(2000)  unique,
        address   varchar(2000) unique
);