package com.example.springboot.controler;

import com.example.springboot.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/url")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public String shortenUrl(@RequestBody String fullUrl) {
        return urlShortenerService.shortenUrl(fullUrl);
    }

    @GetMapping("/{shortUrl}")
    public String getFullUrl(@PathVariable String shortUrl) {
        return urlShortenerService.getFullUrl(shortUrl);
    }
}
