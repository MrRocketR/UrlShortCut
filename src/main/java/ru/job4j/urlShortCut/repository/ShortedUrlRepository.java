package ru.job4j.urlShortCut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.urlShortCut.model.ShortUrl;

@Repository
public interface ShortedUrlRepository extends CrudRepository<ShortUrl, Integer> {

}
