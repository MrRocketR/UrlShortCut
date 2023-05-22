package ru.job4j.urlShortCut.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlShortCut.dto.SiteRegistration;
import ru.job4j.urlShortCut.dto.Statistic;
import ru.job4j.urlShortCut.model.Url;
import ru.job4j.urlShortCut.model.Site;
import ru.job4j.urlShortCut.service.UrlService;
import ru.job4j.urlShortCut.service.SiteService;

import java.util.*;

@Controller
@RestController
@RequestMapping("/shortcuts")
public class ShorCutUrlController {

    private final SiteService siteService;
    private final UrlService urlService;

    public ShorCutUrlController(SiteService siteService, UrlService urlService) {
        this.siteService = siteService;
        this.urlService = urlService;
    }

    @PostMapping("/registration")
    public ResponseEntity<SiteRegistration> registration(@RequestBody String site) {
        SiteRegistration siteRegistration = siteService.registration(site);
        return new ResponseEntity<>(siteRegistration, siteRegistration.isRegistration() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/convert")
    public ResponseEntity<String> convert(@RequestBody String site, @RequestBody String url) {
        String createdCode = urlService.convent(url, site);
        return new ResponseEntity<>(createdCode, HttpStatus.OK);
    }


    @GetMapping("/redirect")
    public ResponseEntity<String> redirect(@RequestParam("code") String code) {
        String url = urlService.redirect(code);
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @GetMapping("/statistic")
    public ResponseEntity<List<Statistic>> statistic(@RequestParam("site") String site) {
        List<Statistic> statistics = urlService.getStatistic(site);
        return new ResponseEntity<>(
                statistics, HttpStatus.OK
        );
    }


}
