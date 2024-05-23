package com.example.carrental.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.carrental.service.CarImageService;
import com.example.carrental.service.CarService;
import com.example.carrental.service.EquipmentService;
import com.example.carrental.service.AdministratorService;
import com.example.carrental.model.Car;

@Controller
public class CarController {
    private CarService carService;
    private CarImageService carImageService;
    private EquipmentService equipmentService;
    private AdministratorService administratorService;

    public CarController(CarService carService, CarImageService carImageService, EquipmentService equipmentService, AdministratorService administratorService) {
        this.carService = carService;
        this.carImageService = carImageService;
        this.equipmentService = equipmentService;
        this.administratorService = administratorService;
    }

    @GetMapping("/showCars")
    public String showCars(Model model, @RequestParam(defaultValue = "All") String stateFilter, @CookieValue(required = false) String passwd) {
        if (!administratorService.checkCookie(passwd)) {
            return "redirect:/";
        }

        if (stateFilter.equals("All")) {
            model.addAttribute("cars", carService.getAllCars());
        } else {
            model.addAttribute("cars", carService.getCarsByState(stateFilter));
        }

        model.addAttribute("carImageService", carImageService);
        model.addAttribute("stateFilter", stateFilter);
        return "home/showCars";
    }
    
    @PostMapping("/newCar")
    public String configureNew(Model model, @RequestParam String stateFilter) {
        model.addAttribute("car", new Car());
        model.addAttribute("action", "insert");
        model.addAttribute("stateFilter", stateFilter);
        return "home/newCar";
    }

    @PostMapping("/editCar")
    public String editCar(Model model, @RequestParam int car_id, @RequestParam String stateFilter) {
        model.addAttribute("car", carService.getCar(car_id));
        model.addAttribute("action", "update");
        model.addAttribute("stateFilter", stateFilter);
        return "home/newCar";
    }

    @PostMapping("/updateDB")
    public String updateDB(Model model, @ModelAttribute Car car, @RequestParam String action, @RequestParam String stateFilter, @RequestParam(required = false) String image) {
        if (image != null) {
            car.decodeBase64StringToImage(image);
        }

        if (action.equals("insert")) {
            carService.insert(car);
            return "redirect:/showCars?stateFilter=" + stateFilter;
        } else {
            carService.update(car);
            return "redirect:/carDetails?car_id=" + car.getCar_id() + "&stateFilter=" + stateFilter;
        }
    }

    @GetMapping("/carDetails")
    public String viewProductDetails(Model model, @RequestParam int car_id, @RequestParam(defaultValue = "All") String stateFilter, @CookieValue(required = false) String passwd) {
        if (!administratorService.checkCookie(passwd)) {
            return "redirect:/";
        }

        model.addAttribute("car", carService.getCar(car_id));
        model.addAttribute("carImages", carImageService.getAllImages(car_id));
        model.addAttribute("equipmentList", equipmentService.getAllEquipment(car_id));
        model.addAttribute("stateFilter", stateFilter);
        return "home/carDetails";
    }

    @PostMapping("/deleteCar")
    public String deleteProduct(@RequestParam int car_id, @RequestParam String stateFilter) {
        carService.delete(car_id);
        return "redirect:/showCars?stateFilter=" + stateFilter;
    }
}