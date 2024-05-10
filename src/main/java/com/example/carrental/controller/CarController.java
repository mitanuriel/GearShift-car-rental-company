package com.example.carrental.controller;

import java.io.IOException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.carrental.service.CarService;
import com.example.carrental.model.Car;

@Controller
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/showCars")
    public String showCars(Model model, @RequestParam String stateFilter) {
        if (stateFilter.equals("All")) {
            model.addAttribute("cars", carService.getAllCars());
        } else {
            model.addAttribute("cars", carService.getCarsByState(stateFilter));
        }

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

    @PostMapping("/notImage")
    public String notImage(Model model, @ModelAttribute Car car, @RequestParam String action, @RequestParam String stateFilter, @RequestParam String oldImage) {
        if ( !(oldImage.equals("No image")) ) {
            car.decodeBase64StringToImage(oldImage);
        }
        
        model.addAttribute("car", car);
        model.addAttribute("action", action);
        model.addAttribute("stateFilter", stateFilter);
        return "home/newCar";
    }

    @PostMapping("/updateDB")
    public String updateDB(Model model, @ModelAttribute Car car, @RequestParam String action, @RequestParam MultipartFile newImage, @RequestParam String oldImage, @RequestParam String stateFilter) throws IOException {
        if ( !(newImage.isEmpty()) ) {
            if (newImage.getContentType().startsWith("image")) {
                car.setImage(newImage.getBytes());
            } else {
                if ( !(oldImage.equals("No image")) ) {
                    model.addAttribute("oldImage", oldImage);
                } else {
                    model.addAttribute("oldImage", "No image");
                }

                model.addAttribute("car", car);
                model.addAttribute("stateFilter", stateFilter);
                model.addAttribute("action", action);
                return "home/isNotImageWarning";
            }
        } else if ( !(oldImage.equals("No image")) ) {
            car.decodeBase64StringToImage(oldImage);
        }

        if (action.equals("insert")) {
            carService.insert(car);
            return "redirect:/showCars?stateFilter=" + stateFilter;
        } else {
            carService.update(car);
            return "redirect:/carDetails?car_id=" + car.getCar_id() + "&stateFilter=" + stateFilter;
        }
    }

    @PostMapping("/deleteImage")
    public String deleteImage(@RequestParam int car_id, @RequestParam String stateFilter) {
        Car car = carService.getCar(car_id);
        car.setImage(null);
        carService.update(car);

        return "redirect:/carDetails?car_id=" + car_id + "&stateFilter=" + stateFilter;
    }

    @GetMapping("/carDetails")
    public String viewProductDetails(Model model, @RequestParam int car_id, @RequestParam String stateFilter) {
        model.addAttribute("car", carService.getCar(car_id));
        model.addAttribute("stateFilter", stateFilter);
        return "home/carDetails";
    }

    @PostMapping("/deleteCar")
    public String deleteProduct(@RequestParam int car_id, @RequestParam String stateFilter) {
        carService.delete(car_id);
        return "redirect:/showCars?stateFilter=" + stateFilter;
    }
}