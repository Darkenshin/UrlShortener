package com.example.springboot.controller;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.service.UrlShortenerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/url")
public class UrlShortenerController {

    @Autowired
    private UrlShortenerService urlShortenerService;

    @PostMapping("/shorten")
    public ResponseEntity<String> shortenUrl(@RequestBody String fullUrl) throws Exception {
        return new ResponseEntity<>(urlShortenerService.shortenUrl(fullUrl),HttpStatus.OK);
    }

    @GetMapping("/{shortUrl}")
    public ResponseEntity<String> getFullUrl(@PathVariable String shortUrl) throws ResourceNotFoundException {
        return new ResponseEntity<>(urlShortenerService.getFullUrl(shortUrl), HttpStatus.OK);
    }
}