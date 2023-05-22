package ru.job4j.urlShortCut.service;

import org.springframework.stereotype.Service;
import ru.job4j.urlShortCut.dto.Statistic;
import ru.job4j.urlShortCut.model.Url;
import ru.job4j.urlShortCut.model.Site;
import ru.job4j.urlShortCut.repository.ShortedUrlRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public String convent(String url, String adress) {
        Optional<Url> check = findByUrl(url);
        if (check.isPresent()) {
            return "";
        }
        Site site  = siteService.findBySiteName(adress);
        String code = generateCode();
        Url shorted = Url.builder().code(code).total(0).url(url).site(site).build();
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

    public List<Statistic> getStatistic(String site) {
        Site fromDb = siteService.findBySiteName(site);
        List<Url> list = repository.findBySite(fromDb);
        List<Statistic> statistics = new ArrayList<>();
        for (Url uri : list) {
            Statistic statistic =  new Statistic(uri.getUrl(), uri.getTotal());
            statistics.add(statistic);
        }
        return statistics;

    }

    public void update(Url url) {
        repository.save(url);
    }

    public Optional<Url> findByUrl(String url) {
        return repository.findByUrl(url);
    }

    public Optional<Url> getByUrl(String url) {
        Optional<Url> optional = repository.findByUrl(url);
        optional.ifPresent(shortUrl -> shortUrl.setTotal(shortUrl.getTotal() + 1));
        return Optional.of(repository.save(optional.get()));
    }

    public Optional<Url> getAndCountByCode(String code) {
        Optional<Url> optional = repository.findByCode(code);
        optional.ifPresent(url -> url.setTotal(url.getTotal() + 1));
        return Optional.of(repository.save(optional.get()));
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
