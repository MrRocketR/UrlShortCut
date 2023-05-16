create table url if not exist(
    id serial primary key NOT NULL,
    login varchar(2000),
    password varchar(2000),
    site varchar(2000)
);