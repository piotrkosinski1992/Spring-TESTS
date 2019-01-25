package com.springboot.tdd.springbootTDD.harder.stuff.service;

import com.springboot.tdd.springbootTDD.harder.stuff.entity.Link;
import com.springboot.tdd.springbootTDD.harder.stuff.repo.LinkRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureTestDatabase
public class CachingTest {

    private static final String LONG_URL = "https://veryLongUrl.com";
    private static final String SHORT_URL = "short.ly";
    private static final Link LINK = new Link(SHORT_URL, LONG_URL);

    @MockBean
    private LinkRepository linkRepository;

    @Autowired
    private UrlShortener urlShortener;

    @Test
    public void checkIfCachingWorks() {

        //arrange
        when(linkRepository.findByShortUrl(anyString())).thenReturn(LINK);

        //act
        urlShortener.expand(SHORT_URL);
        urlShortener.expand(SHORT_URL);

        //assert
        verify(linkRepository).findByShortUrl(anyString());

    }
}
