package com.springboot.tdd.springbootTDD.harder.stuff.controller;


import com.springboot.tdd.springbootTDD.harder.stuff.entity.Link;
import com.springboot.tdd.springbootTDD.harder.stuff.service.UrlShortener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class LinksController {

    @Autowired
    private UrlShortener urlShortener;

    @ResponseStatus(HttpStatus.CREATED)
    @GetMapping("/shorten")
    public Link shorten(@RequestParam("fullUrl") String fullUrl) {
        return urlShortener.shorten(fullUrl);
    }
}
