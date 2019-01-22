package com.springboot.tdd.springbootTDD.app.controller;

import com.springboot.tdd.springbootTDD.app.domain.Car;
import com.springboot.tdd.springbootTDD.app.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CarController {

    @Autowired
    private CarService carService;


    @GetMapping("/cars/{name}")
    public Car getCar(@PathVariable String name) {
        return carService.getCarByName(name);
    }
}
