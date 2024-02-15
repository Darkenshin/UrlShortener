package com.example.springboot.service;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.UrlMapping;
import com.example.springboot.repository.UrlMappingRepository;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class UrlShortenerService {

    private final UrlMappingRepository urlMappingRepository;

    public UrlShortenerService(UrlMappingRepository urlMappingRepository) {
        this.urlMappingRepository = urlMappingRepository;
    }

    public String generateShortUrl(String fullUrl) throws Exception {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(fullUrl.getBytes());
            String base64Hash = Base64.getEncoder().encodeToString(hash);
            return base64Hash.substring(0, 10);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            throw new Exception("Internal Error");
        }
    }

    public String shortenUrl(String fullUrl) throws Exception {

        UrlMapping existingMapping = urlMappingRepository.findByFullUrl(fullUrl);
        if (existingMapping != null) {
            return existingMapping.getShortUrl();
        } else {
            UrlMapping newMapping = new UrlMapping(generateShortUrl(fullUrl), fullUrl);
            urlMappingRepository.save(newMapping);
            return newMapping.getShortUrl();
        }
    }

    public String  getFullUrl(String shortUrl) throws ResourceNotFoundException {
        UrlMapping mapping = urlMappingRepository.findByShortUrl(shortUrl);
        if (mapping != null) {
            return mapping.getFullUrl();
        } else {
            throw new ResourceNotFoundException(shortUrl+": Not found");
        }
    }
}
