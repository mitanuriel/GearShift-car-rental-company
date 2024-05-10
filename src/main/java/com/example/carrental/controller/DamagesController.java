package com.example.carrental.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.carrental.model.Damages;
import com.example.carrental.service.DamagesService;

@Controller
public class DamagesController {
    
    private DamagesService damagesService;

    public DamagesController(DamagesService damagesService) {
        this.damagesService = damagesService;
    }
    
    @GetMapping("/editDamages")
    public String editDamages(Model model, @RequestParam int contract_id) {
        model.addAttribute("contract_id", contract_id);
        return "home/editDamages";
    }

    public String updateDamages(@ModelAttribute Damages damages) {
        damagesService.update(damages);

        return "redirect:/contractDetails";
    }
}
