package ru.job4j.urlShortCut.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.urlShortCut.dto.SiteRegistration;
import ru.job4j.urlShortCut.model.Site;
import ru.job4j.urlShortCut.repository.SiteRepository;

import java.util.Optional;

@Service
public class SiteService  {
    private final SiteRepository repository;
    private final CutterService service;
    private final BCryptPasswordEncoder encoder;



    public SiteService(SiteRepository repository, CutterService service, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.service = service;
        this.encoder = encoder;
    }

    public SiteRegistration registration(String site) {
        Site check = repository.findBySite(site);
        if(check != null) {
            return new SiteRegistration(false, "", "");
        }
        Site created = loginAndPasswordGenerator();
        String password = created.getPassword();
        created.setSite(site);
        created.setPassword(encoder.encode(password));
        repository.save(created);
        return new SiteRegistration(true, created.getLogin(), password);
    }

    public Optional<Site> findByLoginPassword(String login, String password) {
        return repository.findByLoginAndPassword(login, password);
    }

    public Site findBySiteName(String site) {
        return repository.findBySite(site);
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
