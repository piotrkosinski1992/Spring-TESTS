package com.springboot.tdd.springbootTDD.app.controller;


import com.springboot.tdd.springbootTDD.app.domain.Car;
import com.springboot.tdd.springbootTDD.app.exceptions.CarNotFoundException;
import com.springboot.tdd.springbootTDD.app.service.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//SPRING CONTROLLER TEST

@RunWith(SpringRunner.class)
@WebMvcTest(CarController.class) //setup components that are need for MVC
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CarService carService;

    @Test
    public void getCar_shouldReturnCar() throws Exception {

//        given(carService.getCarByName("prius")).willReturn(new Car("prius","hybrid"));
        when(carService.getCarByName(anyString())).thenReturn(new Car("prius","hybrid"));

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/prius"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("prius"))
                .andExpect(jsonPath("type").value("hybrid"));
    }

    @Test
    public void getCar_notFound() throws Exception {
        when(carService.getCarByName(anyString())).thenThrow(new CarNotFoundException());

        mockMvc.perform(MockMvcRequestBuilders.get("/cars/prius"))
                .andExpect(status().isNotFound());
    }



    //albo to albo annotation nad exception jezeli custom
//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.NOT_FOUND)
//    private void carNotFoundHandler(CarNotFoundException ex) {
//    }


}
