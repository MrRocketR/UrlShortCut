create table shortUrl(
    id serial primary not null,
    shortUrl varchar(2000),
    urlId int references url(id),
);