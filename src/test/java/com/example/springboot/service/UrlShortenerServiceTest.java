package com.example.springboot.service;

import com.example.springboot.exception.ResourceNotFoundException;
import com.example.springboot.model.UrlMapping;
import com.example.springboot.repository.UrlMappingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
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
    void shorten_NewUrl_ShouldReturnShortUrl() throws Exception {
        // Given
        String fullUrl = "https://www.example.com";
        String expectedShortUrl = urlShortenerService.generateShortUrl(fullUrl);
        when(urlMappingRepository.findByFullUrl(fullUrl)).thenReturn(null);

        // When
        String actualShortUrl = urlShortenerService.shortenUrl(fullUrl);

        // Then
        assertEquals(expectedShortUrl, actualShortUrl);
    }

    @Test
    void shorten_ExistingUrl_ShouldReturnExistingShortUrl() throws Exception {
        // Given
        String fullUrl = "https://www.example.com";
        String existingShortUrl = "abc123";
        when(urlMappingRepository.findByFullUrl(fullUrl)).thenReturn(new UrlMapping(existingShortUrl, fullUrl));

        // When
        String actualShortUrl = urlShortenerService.shortenUrl(fullUrl);

        // Then
        assertEquals(existingShortUrl, actualShortUrl);
    }

    @Test
    void getFullUrl_ExistingShortUrl_ShouldReturnFullUrl() throws ResourceNotFoundException {
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
    void getFullUrl_NonExistingShortUrl_ShouldThrowException() {
        // Given
        String shortUrl = "nonexisting";

        // When
        Exception exception = assertThrows(ResourceNotFoundException.class, () -> urlShortenerService.getFullUrl(shortUrl));
        // Then
        String expectedMessage = "Not found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));





    }
}
