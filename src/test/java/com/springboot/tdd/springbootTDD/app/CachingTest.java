package com.springboot.tdd.springbootTDD.app;

import com.springboot.tdd.springbootTDD.app.domain.Car;
import com.springboot.tdd.springbootTDD.app.repo.CarRepository;
import com.springboot.tdd.springbootTDD.app.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE) //classes= CachingConfig.class
@AutoConfigureTestDatabase
//@AutoConfigureCache gdy nie chcemy ładować całego kontekstu springowego
public class CachingTest {

    @Autowired
    private CarService carService;

    @MockBean
    private CarRepository carRepository;

    @Test
    public void caching() {
        when(carRepository.findByName(anyString())).thenReturn(Optional.of(new Car("prius","hybrid")));

        carService.getCarByName("prius");
        carService.getCarByName("prius");

        verify(carRepository, times(1)).findByName("prius");
    }
}
