package com.springboot.tdd.springbootTDD.harder.stuff.repo;

import com.springboot.tdd.springbootTDD.harder.stuff.entity.Link;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LinkRepository extends JpaRepository<Link,Long> {
    Link findByFullUrl(String fullUrl);

    Link findByShortUrl(String shortUrl);
}
