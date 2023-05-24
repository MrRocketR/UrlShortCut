package ru.job4j.shortcut.service;

import org.springframework.stereotype.Service;
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
    private final CutterService service;
    private final SiteService siteService;

    public UrlService(ShortedUrlRepository repository, CutterService service, SiteService siteService) {
        this.repository = repository;
        this.service = service;
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
        Site site = siteService.findBySiteName(urlToConvert.getSite());
        String code = generateCode();
        Url shorted = Url.builder().code(code).
                total(0).url(urlToConvert.
                        getUrl()).
                site(site).build();
        repository.save(shorted);
        return code;
    }


    public String redirect(String code) {
        Optional<Url> url = findByCode(code);
        if (url.isEmpty()) {
            return "";
        }
        increment(url.get().getId());
        return url.get().getUrl();

    }

    private void increment(Integer id) {
        repository.incrementTotal(id);
    }

    public Statistic getStatistic(String site) {
        Site fromDb = siteService.findBySiteName(site);
        if (fromDb == null) {
            return null;
        }
        List<Url> list = fromDb.getUrls();
        Statistic statistic = new Statistic();
        statistic.setAddress(site);
        List<UriStat> uriStats = new ArrayList<>();
        for (Url uri : list) {
            UriStat uriStat = new UriStat(uri.getUrl(), uri.getTotal());
            uriStats.add(uriStat);
        }
        statistic.setUrls(uriStats);
        return statistic;

    }

    public Optional<Url> findByUrl(String url) {
        return repository.findByUrl(url);
    }


    private String generateCode() {
        String code = service.generateCode();
        Optional<Url> check = findByCode(code);
        while (check.isPresent()) {
            code = service.generateCode();
            check = findByCode(code);
        }
        return code;

    }


}
