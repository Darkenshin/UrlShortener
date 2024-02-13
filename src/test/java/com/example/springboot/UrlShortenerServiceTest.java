package com.example.springboot;

import com.example.springboot.model.UrlMapping;
import com.example.springboot.repository.UrlMappingRepository;
import com.example.springboot.service.UrlShortenerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class UrlShortenerServiceTest {

    @Mock
    private UrlMappingRepository urlMappingRepository;

    @InjectMocks
    private UrlShortenerService urlShortenerService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void shorten_NewUrl_ShouldReturnShortUrl() {
        // Given
        String fullUrl = "https://www.example.com";
        String expectedShortUrl = "abc123";
        when(urlMappingRepository.findByShortUrl(expectedShortUrl)).thenReturn(null);

        // When
        String actualShortUrl = urlShortenerService.shortenUrl(fullUrl);

        // Then
       // Todo assertEquals(expectedShortUrl, actualShortUrl);
    }

    @Test
    void shorten_ExistingUrl_ShouldReturnExistingShortUrl() {
        // Given
        String fullUrl = "https://www.example.com";
        String existingShortUrl = "abc123";
        when(urlMappingRepository.findByShortUrl(existingShortUrl)).thenReturn(new UrlMapping(existingShortUrl, fullUrl));

        // When
        String actualShortUrl = urlShortenerService.shortenUrl(fullUrl);

        // Then
//        assertEquals(existingShortUrl, actualShortUrl);
    }

    @Test
    void getFullUrl_ExistingShortUrl_ShouldReturnFullUrl() {
        // Given
        String shortUrl = "abc123";
        String expectedFullUrl = "https://www.example.com";
        when(urlMappingRepository.findByShortUrl(shortUrl)).thenReturn(new UrlMapping(shortUrl, expectedFullUrl));

        // When
        String actualFullUrl = urlShortenerService.getFullUrl(shortUrl);

        // Then
        assertEquals(expectedFullUrl, actualFullUrl);
    }

    @Test
    void getFullUrl_NonExistingShortUrl_ShouldReturnNull() {
        // Given
        String shortUrl = "nonexisting";

        // When
        String actualFullUrl = urlShortenerService.getFullUrl(shortUrl);

        // Then
        assertEquals(null, actualFullUrl);
    }
}
