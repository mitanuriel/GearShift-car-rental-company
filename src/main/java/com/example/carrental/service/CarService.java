package com.example.carrental.service;

import org.springframework.stereotype.Service;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.model.Car;

import java.util.List;


@Service
public class CarService {
    private CarRepository carRepository;
    //lavet af Oliver
    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    //lavet af Oliver
    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }
    //lavet af Oliver
    public Car getCar(int car_id) {
        return carRepository.getCar(car_id);
    }
    //lavet af Oliver
    public List<Car> getCarsByState(String state) {
        return carRepository.getCarsByState(state);
    }
    //lavet af Oliver
    public void insert(Car car) {
        carRepository.insert(car);
    }
    //lavet af Oliver
    public void delete(int car_id) {
        carRepository.delete(car_id);
    }
    //lavet af Oliver
    public void update(Car car) {
        carRepository.update(car);
    }

    //lavet af Hung
    public List<Car> getCar1(String chassisnumber) {
        return carRepository.getCarid(chassisnumber);
    }
}