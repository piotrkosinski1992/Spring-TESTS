package com.springboot.tdd.springbootTDD.harder.stuff.service;

import com.springboot.tdd.springbootTDD.harder.stuff.entity.Link;
import com.springboot.tdd.springbootTDD.harder.stuff.repo.LinkRepository;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import com.google.common.hash.Hashing;

import java.nio.charset.StandardCharsets;

@Service
public class UrlShortener {
    private LinkRepository linkRepository;

    public UrlShortener(LinkRepository linkRepository) {
        this.linkRepository = linkRepository;
    }

    public Link shorten(String fullUrl) {
        Link link = linkRepository.findByFullUrl(fullUrl);
        if(link == null) {
            String shortUrl = Hashing.murmur3_32().hashString(fullUrl, StandardCharsets.UTF_8).toString();
            link = new Link(shortUrl, fullUrl);
            linkRepository.save(link);
        }
        return link;
    }



    @Cacheable("link")
    public Link expand(String shortUrl) {
        return linkRepository.findByShortUrl(shortUrl);
    }
}