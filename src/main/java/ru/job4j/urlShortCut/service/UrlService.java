package ru.job4j.urlShortCut.service;

import org.springframework.stereotype.Service;
import ru.job4j.urlShortCut.repository.UrlRepository;

import java.util.Map;

@Service
public class UrlService {
    private final UrlRepository repository;
    private final CutterService service;

    public UrlService(UrlRepository repository, CutterService service) {
        this.repository = repository;
        this.service = service;
    }

    public Map<String, String> registration() {

    }

    private Map<String, String>
}
