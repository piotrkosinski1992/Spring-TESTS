package com.springboot.tdd.springbootTDD.harder.stuff.repo;


import com.springboot.tdd.springbootTDD.harder.stuff.entity.Link;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LinkRepositoryTest {

    private static final String LONG_URL = "https://veryLongUrl.com";
    private static final String SHORT_URL = "short.ly";

    @Autowired
    private LinkRepository linkRepository;

    @After
    public void tearDown() {
        linkRepository.deleteAll();
    }

    @Test
    public void findByFullUrl_shouldFetchUrlMatch() {

        //arrange
        linkRepository.save(new Link(SHORT_URL, LONG_URL));

        //act
        Link foundLink = linkRepository.findByFullUrl(LONG_URL);

        //asserts
        assertThat(foundLink).isNotNull();
        assertThat(foundLink.getShortUrl()).isEqualTo(SHORT_URL);
        assertThat(foundLink.getFullUrl()).isEqualTo(LONG_URL);


    }
}