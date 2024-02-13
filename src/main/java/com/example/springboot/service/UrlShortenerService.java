package com.example.springboot.service;
import com.example.springboot.model.UrlMapping;
import com.example.springboot.repository.UrlMappingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

@Service
public class UrlShortenerService {

    @Autowired
    private UrlMappingRepository urlMappingRepository;

    public String generateShortUrl(String fullUrl) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(fullUrl.getBytes());
            String base64Hash = Base64.getEncoder().encodeToString(hash);
            return base64Hash.substring(0, 10);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String shortenUrl(String fullUrl) {
        String shortUrl = generateShortUrl(fullUrl);
        UrlMapping existingMapping = urlMappingRepository.findByShortUrl(shortUrl);
        if (existingMapping != null) {
            return existingMapping.getShortUrl();
        } else {
            UrlMapping newMapping = new UrlMapping(shortUrl, fullUrl);
            urlMappingRepository.save(newMapping);
            return shortUrl;
        }
    }

    public String getFullUrl(String shortUrl) {
        UrlMapping mapping = urlMappingRepository.findByShortUrl(shortUrl);
        if (mapping != null) {
            return mapping.getFullUrl();
        } else {
            return null;
        }
    }
}
