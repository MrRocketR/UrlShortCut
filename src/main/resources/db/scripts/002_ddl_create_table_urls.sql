create table  if not exists urls(
    id serial primary key NOT NULL,
    code varchar(2000) unique,
    total int,
    site_id int references sites(id)
);