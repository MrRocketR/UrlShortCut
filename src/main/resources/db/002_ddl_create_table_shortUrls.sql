create table shortUrl if not exist(
    id serial primary not null,
    code varchar(2000),
    total int,
    urlId int references url(id),
);