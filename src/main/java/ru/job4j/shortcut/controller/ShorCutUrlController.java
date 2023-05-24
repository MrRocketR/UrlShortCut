package ru.job4j.shortcut.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.shortcut.dto.SiteRegistration;
import ru.job4j.shortcut.dto.SiteRequest;
import ru.job4j.shortcut.dto.Statistic;
import ru.job4j.shortcut.dto.UriConvert;
import ru.job4j.shortcut.service.UrlService;
import ru.job4j.shortcut.service.SiteService;


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
    public ResponseEntity<SiteRegistration> registration(@RequestBody SiteRequest site) {
        SiteRegistration siteRegistration = siteService.registration(site);
        if (!siteRegistration.isRegistration()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Это сайт уже есть в системе");
        }
        return new ResponseEntity<>(siteRegistration, siteRegistration.isRegistration() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/convert")
    public ResponseEntity<String> convert(@RequestBody UriConvert url) {
        String createdCode = urlService.convent(url);
        if (createdCode.length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Этот URL уже есть в системе");
        }
        return new ResponseEntity<>(createdCode, HttpStatus.OK);
    }


    @GetMapping("/redirect/{code}")
    public ResponseEntity<String> redirect(@PathVariable("code") String code) {
        String url = urlService.redirect(code);
        if (url.length() == 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Не найден URL по данному коду");
        }
        return new ResponseEntity<>(url, HttpStatus.OK);
    }

    @GetMapping("/statistic/{site}")
    public ResponseEntity<Statistic> statistic(@PathVariable("site") String site) {
        Statistic statistics = urlService.getStatistic(site);
        if (statistics == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Не найден адресс");
        }
        return new ResponseEntity<>(
                statistics, HttpStatus.OK
        );
    }


}
