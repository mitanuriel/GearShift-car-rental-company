package com.example.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.carrental.service.CarService;
import com.example.carrental.model.Car;

@Controller
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/showCars")
    public String index(Model model) {
        model.addAttribute("cars", carService.getAllCars());
        return "home/showCars";
    }

    
    @GetMapping("/newCar")
    public String configureNew(Model model) {
        model.addAttribute("car", new Car());
        model.addAttribute("action", "insert");
        return "home/newCar";
    }

    @GetMapping("/editCar")
    public String editCar(Model model, @RequestParam int car_id) {
        model.addAttribute("car", carService.getCar(car_id));
        model.addAttribute("action", "update");
        return "home/newCar";
    }

    @PostMapping("/updateDB")
    public String updateDB(@ModelAttribute Car car, @RequestParam String action) {
        if (action.equals("insert")) {
            carService.insert(car);
            return "redirect:/showCars";
        } else {
            carService.update(car);
            return "redirect:/carDetails?car_id=" + car.getCar_id();
        }
    }

    @GetMapping("/carDetails")
    public String viewProductDetails(Model model, @RequestParam int car_id) {
        model.addAttribute("car", carService.getCar(car_id));
        return "home/carDetails";
    }

    @PostMapping("/deleteCar")
    public String deleteProduct(@RequestParam int car_id) {
        carService.delete(car_id);
        return "redirect:/showCars";
    }

    

    
    
}