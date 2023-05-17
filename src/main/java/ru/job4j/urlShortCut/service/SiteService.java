package ru.job4j.urlShortCut.service;

import org.springframework.stereotype.Service;
import ru.job4j.urlShortCut.model.Site;
import ru.job4j.urlShortCut.repository.SiteRepository;

import java.util.Optional;


public class SiteService {
    private final SiteRepository repository;
    private final CutterService service;

    public SiteService(SiteRepository repository, CutterService service) {
        this.repository = repository;
        this.service = service;
    }

    public Optional<Site> registration(String site) {
        Site created = loginAndPasswordGenerator();
        created.setSite(site);
        return Optional.of(repository.save(created));
    }

    public Optional<Site> findByLoginPassword(String login, String password) {
        return repository.findByLoginAndPassword(login, password);
    }


    private Site loginAndPasswordGenerator() {
        String login = service.generateCode();
        String password = service.generateCode();
        Optional<Site> optional = findByLoginPassword(login, password);
        while (optional.isPresent()) {
            login = service.generateCode();
            password = service.generateCode();
            optional = findByLoginPassword(login, password);
        }
        Site created = new Site();
        created.setLogin(login);
        created.setPassword(password);
        return created;
    }


}
