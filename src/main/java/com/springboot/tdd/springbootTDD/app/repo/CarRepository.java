package com.springboot.tdd.springbootTDD.app.repo;

import com.springboot.tdd.springbootTDD.app.domain.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car,Integer> {
    Optional<Car> findByName(String name);
}
