package com.example.carrental.controller;

import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.carrental.model.CarImage;
import com.example.carrental.service.CarImageService;

@Controller
public class CarImageController {
    private CarImageService carImageService;

    public CarImageController(CarImageService carImageService) {
        this.carImageService = carImageService;
    }
    
    @PostMapping("/deleteAdditionalImage")
    public String deleteAdditionalImage(@RequestParam int car_image_id, @RequestParam int car_id, @RequestParam String stateFilter) {
        carImageService.delete(car_image_id);
        
        return "redirect:/carDetails?car_id=" + car_id + "&stateFilter=" + stateFilter;
    }

    @PostMapping("/addAdditionalImage")
    public String addAdditionalImage(Model model, @RequestParam int car_id, @RequestParam String stateFilter, @RequestParam MultipartFile newImage) throws IOException {
        if ( !(newImage.isEmpty()) ) {
            if (newImage.getContentType().startsWith("image")) {
                carImageService.insert(new CarImage(newImage.getBytes(), car_id));
            } else {
                model.addAttribute("car_id", car_id);
                model.addAttribute("stateFilter", stateFilter);

                return "home/isNotImageWarning";
            }
        }

        return "redirect:/carDetails?car_id=" + car_id + "&stateFilter=" + stateFilter;
    }
}