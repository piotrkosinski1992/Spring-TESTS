package com.springboot.tdd.springbootTDD.harder.stuff.service;

import com.springboot.tdd.springbootTDD.harder.stuff.entity.Link;
import com.springboot.tdd.springbootTDD.harder.stuff.repo.LinkRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UrlShortenerTest {

    private static final String LONG_URL = "https://veryLongUrl.com";
    private static final String SHORT_URL = "short.ly";
    private static final Link LINK = new Link(SHORT_URL, LONG_URL);

    private UrlShortener urlShortener;

    @Mock
    private LinkRepository linkRepository;

    @Before
    public void setUp() {
        urlShortener = new UrlShortener(linkRepository);
    }

    @Test
    public void shorten_lookUpDatabase_AndReturnsShortenedUrl() {

        //arrange
        when(linkRepository.findByFullUrl(anyString())).thenReturn(LINK);

        //act
        Link resultLink = urlShortener.shorten(LONG_URL);

        //assert
        assertThat(resultLink.getShortUrl()).isEqualTo(LINK.getShortUrl());
        verify(linkRepository).findByFullUrl(anyString());
    }

    @Test
    public void shorten_shouldShortenUrl_whenLookupFails() throws Exception {
        //arrange
        when(linkRepository.findByFullUrl(anyString())).thenReturn(null);

        //act
        Link resultLink = urlShortener.shorten(LONG_URL);

        //assert
        assertThat(resultLink.getShortUrl()).isNotNull();
        verify(linkRepository).findByFullUrl(anyString());
        verify(linkRepository).save(resultLink);
    }
}