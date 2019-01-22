package com.springboot.tdd.springbootTDD.app.service;

import com.springboot.tdd.springbootTDD.app.domain.Car;
import com.springboot.tdd.springbootTDD.app.exceptions.CarNotFoundException;
import com.springboot.tdd.springbootTDD.app.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Cacheable("cars")
    public Car getCarByName(String name) {
        return carRepository.findByName(name)
                            .orElseThrow(() -> new CarNotFoundException("No car with name " + name ));
    }

}
