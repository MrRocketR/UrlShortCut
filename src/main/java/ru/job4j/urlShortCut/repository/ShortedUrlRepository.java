package ru.job4j.urlShortCut.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ru.job4j.urlShortCut.model.Site;
import ru.job4j.urlShortCut.model.Url;

import java.util.List;
import java.util.Optional;


public interface ShortedUrlRepository extends CrudRepository<Url, Integer> {
        public Optional<Url> findByCode(String code);
        public Optional<Url> findByUrl(String url);


        public List<Url> findBySite(Site site);

        @Modifying
        @Query("update Url u set u.total = (u.total +1 ) where u.id = :id ")
        void incrementTotal(@Param(value = "id") long id);




}
