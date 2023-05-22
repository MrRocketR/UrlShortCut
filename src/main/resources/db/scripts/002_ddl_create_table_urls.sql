create table  if not exists urls(
    id serial primary key NOT NULL,
    code varchar(2000),
    total int,
    siteId int references sites(id)
);