package com.springboot.tdd.springbootTDD;

import com.springboot.tdd.springbootTDD.app.domain.Car;
import com.springboot.tdd.springbootTDD.app.repo.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class SpringbootTddApplication implements CommandLineRunner {

	@Autowired
	private CarRepository carRepository;

	public static void main(String[] args) {
		SpringApplication.run(SpringbootTddApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		carRepository.save(new Car("prius","hybrid"));
	}
}

