package ru.job4j.shortcut.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.shortcut.model.Site;

import java.util.Optional;


public interface SiteRepository extends CrudRepository<Site, Integer> {

    Optional<Site> findByLoginAndPassword(String login, String password);

    Site findByAddress(String address);

    Site findByLogin(String s);

}
