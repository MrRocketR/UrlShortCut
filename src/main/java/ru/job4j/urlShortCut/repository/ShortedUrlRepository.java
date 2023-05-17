package ru.job4j.urlShortCut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.urlShortCut.model.ShortUrl;

import java.util.Optional;


public interface ShortedUrlRepository extends CrudRepository<ShortUrl, Integer> {
        public Optional<ShortUrl> findByCode(String code);
        public Optional<ShortUrl> findByUrl(String url);


}
