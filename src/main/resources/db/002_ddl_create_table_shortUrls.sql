create table shortUrl if not exist(
    id serial primary key NOT NULL,
    code varchar(2000),
    total int,
    urlId int references url(id),
);