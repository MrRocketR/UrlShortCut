package ru.job4j.urlShortCut.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.urlShortCut.model.ShortUrl;
import ru.job4j.urlShortCut.model.Site;
import ru.job4j.urlShortCut.repository.ShortedUrlRepository;

import java.util.Optional;


public class ShortedUrlService {

    private final ShortedUrlRepository repository;
    private final CutterService service;

    public ShortedUrlService(ShortedUrlRepository repository, CutterService service) {
        this.repository = repository;
        this.service = service;
    }

    public Optional<ShortUrl> findByCode(String code) {
        return repository.findByCode(code);
    }

    public void update (ShortUrl shortUrl) {
        repository.save(shortUrl);
    }

    public Optional<ShortUrl> findByUrl(String url) {
        return repository.findByUrl(url);
    }

    public Optional<ShortUrl> getByUrl(String url) {
        Optional<ShortUrl> optional = repository.findByUrl(url);
        optional.ifPresent(shortUrl -> shortUrl
                .setTotal(shortUrl.getTotal() + 1));
        return Optional.of(repository.save(optional.get()));
    }

    public Optional<ShortUrl> getByCode(String code) {
        Optional<ShortUrl> optional = repository.findByCode(code);
        optional.ifPresent(shortUrl -> shortUrl
                .setTotal(shortUrl.getTotal() + 1));
        return Optional.of(repository.save(optional.get()));
    }

    public Optional<ShortUrl> convent(String url, Site site) {
        String code =  generateCode();
        ShortUrl shorted = ShortUrl
                .builder()
                .code(code)
                .total(0)
                .url(url)
                .site(site)
                .build();
       return Optional.of(repository.save(shorted));
    }

    private String  generateCode() {
        String code = service.generateCode();
            Optional<ShortUrl> check = findByCode(code);
            while (check.isPresent()) {
                code = service.generateCode();
                check = findByCode(code);
            }
            return code;

    }



}
