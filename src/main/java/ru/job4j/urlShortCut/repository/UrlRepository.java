package ru.job4j.urlShortCut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.urlShortCut.model.Url;
@Repository
public interface UrlRepository extends CrudRepository<Url, Integer> {
}
