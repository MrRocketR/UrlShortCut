package ru.job4j.urlShortCut.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.urlShortCut.model.Site;

import java.util.Optional;


public interface SiteRepository extends CrudRepository<Site, Integer> {

    Optional<Site> findByLoginAndPassword(String login, String password);

}
