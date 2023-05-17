package ru.job4j.urlShortCut.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlShortCut.model.ShortUrl;
import ru.job4j.urlShortCut.model.Site;
import ru.job4j.urlShortCut.service.ShortedUrlService;
import ru.job4j.urlShortCut.service.SiteService;

import java.util.*;

@Controller
@RestController
@RequestMapping("/shortCutter")
public class ShorCutUrlController {

    private final SiteService siteService;
    private final ShortedUrlService shortedUrlService;

    public ShorCutUrlController(SiteService siteService, ShortedUrlService shortedUrlService) {
        this.siteService = siteService;
        this.shortedUrlService = shortedUrlService;
    }

    @PostMapping("/registration")
    public List<String> registration(String site) {
        Optional<Site>  created = siteService.registration(site);
        if(created.isPresent()) {
            List<String> response = new ArrayList<>();
            response.add(created.get().getLogin());
            response.add(created.get().getPassword());
            return ResponseEntity.ok(response).getBody();
        }

        return null;
    }

    @PostMapping("/authorization")
    public String authorization(@RequestParam("value") String value
    , @RequestParam("value") String vale)
     {



        return ResponseEntity.ok("Success").getBody();
    }



    @PostMapping("/convert")
    public ResponseEntity<String> convert( @RequestHeader("Authorization") String bearerToken,
                                         @RequestParam("url") String url) {
        if (bearerToken == null || bearerToken.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing Bearer token");
        }
       // Optional<ShortUrl> shorted = shortedUrlService.convent();

        return null;
    }

    @GetMapping("/redirect")
    public ResponseEntity<String> redirect(@RequestParam("code") String code) {
        Optional<ShortUrl> optional = shortedUrlService.getAndCountByCode(code);
        return optional.map(shortUrl -> ResponseEntity.ok(shortUrl.getUrl())).orElse(null);
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<String>> statistic(@RequestParam("url") String url) {
        Optional<ShortUrl> optional = shortedUrlService.findByUrl(url);
        if(optional.isPresent()) {
            List<String> response = new ArrayList<>();
            response.add(optional.get().getUrl());
            response.add(String.valueOf(optional.get().getTotal()));
            return ResponseEntity.ok(response);
        }
        return null;
    }
}
