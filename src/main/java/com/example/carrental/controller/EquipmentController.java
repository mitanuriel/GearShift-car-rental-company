package com.example.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.carrental.model.Equipment;
import com.example.carrental.service.EquipmentService;
import com.example.carrental.service.AdministratorService;

@Controller
public class EquipmentController {
    private EquipmentService equipmentService;
    private AdministratorService administratorService;

    public EquipmentController(EquipmentService equipmentService, AdministratorService administratorService) {
        this.equipmentService = equipmentService;
        this.administratorService = administratorService;
    }
    
    @GetMapping("/editEquipment")
    public String editEquipment(Model model, @RequestParam int car_id, @RequestParam(defaultValue = "All") String stateFilter, @CookieValue(required = false) String passwd) {
        if (!administratorService.checkCookie(passwd)) {
            return "redirect:/";
        }

        model.addAttribute("car_id", car_id);
        model.addAttribute("stateFilter", stateFilter);
        model.addAttribute("equipmentList", equipmentService.getAllEquipment(car_id));
        model.addAttribute("newEquipment", new Equipment(car_id));
        
        return "home/editEquipment";
    }

    @PostMapping("/deleteEquipment")
    public String updateEquipment(Model model, @RequestParam int equipment_id, @RequestParam int car_id, @RequestParam String stateFilter) {
        equipmentService.delete(equipment_id);

        return "redirect:/editEquipment?car_id=" + car_id + "&stateFilter=" + stateFilter;
    }

    @PostMapping("/insertEquipment")
    public String insertEquipment(Model model, @ModelAttribute Equipment equipment, @RequestParam String stateFilter) {
        equipmentService.insert(equipment);

        return "redirect:/editEquipment?car_id=" + equipment.getCar_id() + "&stateFilter=" + stateFilter;
    }
}
