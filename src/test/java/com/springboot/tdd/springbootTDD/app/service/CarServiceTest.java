package com.springboot.tdd.springbootTDD.app.service;

import com.springboot.tdd.springbootTDD.app.domain.Car;
import com.springboot.tdd.springbootTDD.app.exceptions.CarNotFoundException;
import com.springboot.tdd.springbootTDD.app.repo.CarRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

//SPRING UNIT TEST
//@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class CarServiceTest {

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarService carService;

    @Test
    public void getCarTest() {
        when(carRepository.findByName(anyString())).thenReturn(Optional.of(new Car("prius","hybrid")));

        Car car = carService.getCarByName(anyString());

        assertThat(car.getName().equals("prius"));
        assertThat(car.getType().equals("hybrid"));
    }

    @Test(expected = CarNotFoundException.class)
    public void getCarNotFoundTest() {
        when(carRepository.findByName(anyString())).thenReturn(Optional.empty());

        carService.getCarByName(anyString());
    }
}
