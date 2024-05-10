package com.example.carrental.service;

import org.springframework.stereotype.Service;
import com.example.carrental.repository.CarRepository;
import com.example.carrental.model.Car;

import java.util.List;


@Service
public class CarService {
    private CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.getAllCars();
    }

    public Car getCar(int car_id) {
        return carRepository.getCar(car_id);
    }

    public List<Car> getCarsByState(String state) {
        return carRepository.getCarsByState(state);
    }

    public void insert(Car car) {
        carRepository.insert(car);
    }

    public void delete(int car_id) {
        carRepository.delete(car_id);
    }

    public void update(Car car) {
        carRepository.update(car);
    }
}