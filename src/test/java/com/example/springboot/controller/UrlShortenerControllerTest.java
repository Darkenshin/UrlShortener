package com.example.springboot.controller;

import com.example.springboot.service.UrlShortenerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class UrlShortenerControllerTest {

	private MockMvc mockMvc;

	@Mock
	private UrlShortenerService urlShortenerService;

	@InjectMocks
	private UrlShortenerController urlShortenerController;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(urlShortenerController).build();
	}

	@Test
	void shortenUrl_ValidUrl_ShouldReturnShortenedUrl() throws Exception {
		// Given
		String fullUrl = "https://www.example.com";
		String shortUrl = "abc123";
		when(urlShortenerService.shortenUrl(anyString())).thenReturn(shortUrl);

		// When/Then
		mockMvc.perform(post("/url/shorten")
						.contentType(MediaType.APPLICATION_JSON)
						.content(fullUrl))
				.andExpect(status().isOk())
				.andExpect(content().string(shortUrl));
	}

	@Test
	void getFullUrl_ExistingShortUrl_ShouldReturnFullUrl() throws Exception {
		// Given
		String shortUrl = "abc123";
		String fullUrl = "https://www.example.com";
		when(urlShortenerService.getFullUrl(shortUrl)).thenReturn(fullUrl);

		// When/Then
		mockMvc.perform(get("/url/{shortUrl}", shortUrl))
				.andExpect(status().isOk())
				.andExpect(content().string(fullUrl));
	}
}
