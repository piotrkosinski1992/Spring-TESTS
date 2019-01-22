package com.springboot.tdd.springbootTDD.app.repo;


import com.springboot.tdd.springbootTDD.app.domain.Car;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

// REPOSITORY TEST stores data in inmemory database

@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void getCar_ReturnsCarDetails() {

        Car savedCar =entityManager.persistAndFlush(new Car("prius","hybrid"));
        Car car = carRepository.findByName("prius").get();

        assertThat(car.getName()).isEqualTo("prius");
    }
}