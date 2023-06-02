package ru.job4j.shortcut.service;

import org.springframework.stereotype.Service;
import ru.job4j.shortcut.dto.SiteRegistration;
import ru.job4j.shortcut.dto.Statistic;
import ru.job4j.shortcut.dto.UriStat;
import ru.job4j.shortcut.dto.UriConvert;
import ru.job4j.shortcut.model.Url;
import ru.job4j.shortcut.model.Site;
import ru.job4j.shortcut.repository.ShortedUrlRepository;

import java.util.*;

@Service
public class UrlService {

    private final ShortedUrlRepository repository;
    private final SiteService siteService;

    public UrlService(ShortedUrlRepository repository, SiteService siteService) {
        this.repository = repository;
        this.siteService = siteService;
    }

    public Optional<Url> findByCode(String code) {
        return repository.findByCode(code);
    }

    public String convent(UriConvert urlToConvert) {
        Optional<Url> check = findByUrl(urlToConvert.getUrl());
        if (check.isPresent()) {
            return "";
        }
       Optional<Site> site = siteService.findBySiteName(urlToConvert.getSite());
        String code = generateCode();
        Url shorted = Url.builder().code(code).
                total(0).url(urlToConvert.
                getUrl()).
                site(site.get()).build();
        repository.save(shorted);
        return code;
    }


    public String redirect(String code) {
        Optional<Url> url = findByCode(code);
        if (url.isEmpty()) {
            return "";
        }
        repository.incrementTotal(url.get().getId());
        return url.get().getUrl();

    }

    public Optional<Statistic> getStatistic(String site) {
       Optional<Site> fromDb = siteService.findBySiteName(site);
        if (fromDb.isEmpty()) {
           return Optional.empty();
        }
        List<Url> list = fromDb.get().getUrls();
        Statistic statistic = new Statistic();
        statistic.setAddress(site);
        List<UriStat> uriStats = new ArrayList<>();
        for (Url uri : list) {
            UriStat uriStat = new UriStat(uri.getUrl(), uri.getTotal());
            uriStats.add(uriStat);
        }
        statistic.setUrls(uriStats);
        return Optional.of(statistic);

    }

    public Optional<Url> findByUrl(String url) {
        return repository.findByUrl(url);
    }


    private String generateCode() {
        String code;
        Optional<Url> check;
        do {
            code = CutterService.generateCode();
            check = findByCode(code);
        }
        while (check.isPresent());
        return code;

    }

}
