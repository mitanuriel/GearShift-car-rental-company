package com.example.carrental.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.carrental.model.CarImage;
import com.example.carrental.model.Car;
import com.example.carrental.service.AdministratorService;
import com.example.carrental.service.CarImageService;
import com.example.carrental.service.CarService;

@Controller
public class CarImageController {
    private CarImageService carImageService;
    private CarService carService;
    private AdministratorService administratorService;

    public CarImageController(CarImageService carImageService, CarService carService, AdministratorService administratorService) {
        this.carImageService = carImageService;
        this.carService = carService;
        this.administratorService = administratorService;
    }
    
    @PostMapping("/deleteAdditionalImage")
    public String deleteAdditionalImage(@RequestParam int car_image_id, @RequestParam int car_id, @RequestParam String stateFilter) {
        carImageService.delete(car_image_id);
        
        return "redirect:/editImages?car_id=" + car_id + "&stateFilter=" + stateFilter;
    }

    @PostMapping("/addAdditionalImage")
    public String addAdditionalImage(Model model, @RequestParam int car_id, @RequestParam String stateFilter, @RequestParam MultipartFile image) throws IOException {
        if ( !(image.isEmpty()) ) {
            if (image.getContentType().startsWith("image")) {
                carImageService.insert(new CarImage(image.getBytes(), car_id));
            } else {
                model.addAttribute("car_id", car_id);
                model.addAttribute("stateFilter", stateFilter);

                return "home/isNotImageWarning";
            }
        }

        return "redirect:/editImages?car_id=" + car_id + "&stateFilter=" + stateFilter;
    }

    @GetMapping("/editImages")
    public String editImages(Model model, @RequestParam int car_id, @RequestParam(defaultValue = "All") String stateFilter, @CookieValue(required = false) String passwd) {
        if (!administratorService.checkCookie(passwd)) {
            return "redirect:/";
        }

        model.addAttribute("car", carService.getCar(car_id));
        model.addAttribute("stateFilter", stateFilter);
        model.addAttribute("carImages", carImageService.getAllImages(car_id));
        return "home/editImages";
    }

    @PostMapping("/saveImage")
    public String saveImage(Model model, @RequestParam int car_id, @RequestParam String stateFilter, @RequestParam MultipartFile image) throws IOException {
        if ( !(image.isEmpty()) ) {
            if (image.getContentType().startsWith("image")) {
                Car car = carService.getCar(car_id);
                car.setImage(image.getBytes());
                carService.update(car);
            } else {
                model.addAttribute("car_id", car_id);
                model.addAttribute("stateFilter", stateFilter);
                return "home/isNotImageWarning";
            }
        }

        return "redirect:/editImages?car_id=" + car_id + "&stateFilter=" + stateFilter;
    }

    @PostMapping("/deleteImage")
    public String deleteImage(@RequestParam int car_id, @RequestParam String stateFilter) {
        Car car = carService.getCar(car_id);
        car.setImage(null);
        carService.update(car);

        return "redirect:/editImages?car_id=" + car_id + "&stateFilter=" + stateFilter;
    }
}