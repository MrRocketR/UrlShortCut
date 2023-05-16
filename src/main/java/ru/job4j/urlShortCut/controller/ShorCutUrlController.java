package ru.job4j.urlShortCut.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.job4j.urlShortCut.model.Site;

@Controller
@RestController
@RequestMapping("/shortCutter")
public class ShorCutUrlController {

    @PostMapping("/registration")
    public ResponseEntity<Site> registration(String site) {
        return null;
    }

    @PostMapping("/authorization")
    public ResponseEntity<Site> Authorization(String login, String password) {
        return null;
    }

    @PostMapping("/convert")
    public ResponseEntity<Void> convert(@RequestBody String link) {
        return null;
    }

    @GetMapping("/redirect")
    public ResponseEntity<Void> redirect() {
        return null;
    }

    @GetMapping("/statistic")
    public ResponseEntity<Void> statistic() {
        return null;
    }
}
