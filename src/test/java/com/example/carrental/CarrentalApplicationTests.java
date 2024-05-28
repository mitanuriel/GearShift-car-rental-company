package com.example.carrental;

import com.example.carrental.model.Car;
import com.example.carrental.repository.CarRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class CarrentalApplicationTests {

    @Autowired
    CarRepository carRepository;



    @Test
    void testforseallebiler(){
        List<Car> cars = carRepository.getAllCars();
        System.out.println(cars.size());
        Assertions.assertNotNull(cars);
    }



    @Test
    void testdelete(){
        List<Car> carsBefore = carRepository.getAllCars();
        int beforeDeleted = carsBefore.size();
        // Vi skal vælge en bil id at slette, vi vælger en arbitrær værdi fra tabellen.
        // Vi tager i det her eksempel car id 30
        carRepository.delete(3);
        List<Car> carsAfter = carRepository.getAllCars();
        int afterDeleted = carsAfter.size();
        Assertions.assertEquals(afterDeleted, beforeDeleted-1);
        //Succesfully
    }




}
