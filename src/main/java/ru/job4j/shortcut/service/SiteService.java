package ru.job4j.shortcut.service;

import org.postgresql.util.PSQLException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.shortcut.dto.SiteRegistration;
import ru.job4j.shortcut.dto.SiteRequest;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.repository.SiteRepository;
import java.util.ArrayList;
import java.util.Optional;


@Service
public class SiteService implements UserDetailsService {
    private final SiteRepository repository;
    private final BCryptPasswordEncoder encoder;
    private static final Logger LOG = LoggerFactory.getLogger(SiteService.class);

    public SiteService(SiteRepository repository, BCryptPasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    public SiteRegistration registration(SiteRequest siteRequest) {
        Site check = null;
        String address  = siteRequest.getSite();
        Site created = loginAndPasswordGenerator();
        String password = created.getPassword();
        created.setAddress(address);
        created.setPassword(encoder.encode(password));
        try {
           check = repository.save(created);
        } catch (Exception e) {
            LOG.error("SQL EX. This site is already in DB");
        }
        SiteRegistration registration = new SiteRegistration(false, "", "");
        if (check != null) {
            registration.setRegistration(true);
            registration.setLogin(check.getLogin());
            registration.setPassword(password);
        } 
        return registration;
    }

    public Optional<Site> findByLoginPassword(String login, String password) {
        return repository.findByLoginAndPassword(login, password);
    }

    public Optional<Site> findBySiteName(String site) {
        return repository.findByAddress(site);
    }

    private Site loginAndPasswordGenerator() {
        String login = CutterService.generateCode();
        String password = CutterService.generateCode();
        Optional<Site> optional = findByLoginPassword(login, password);
        while (optional.isPresent()) {
            login = CutterService.generateCode();
            password = CutterService.generateCode();
            optional = findByLoginPassword(login, password);
        }
        Site created = new Site();
        created.setLogin(login);
        created.setPassword(password);
        return created;
    }


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Site site = repository.findByLogin(s);
        if (site == null) {
            throw new UsernameNotFoundException(String.format("Site with login: %s not found", s));
        }
        return toSite(site);
    }

    private UserDetails toSite(Site site) {
        return User
                .withUsername(site.getLogin())
                .password(site.getPassword())
                .authorities(new ArrayList<>())
                .build();
    }


}
