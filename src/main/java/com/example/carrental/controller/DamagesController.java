package com.example.carrental.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
    
    @PostMapping("/updateDamages")
    public String updateDamages(@ModelAttribute Damages damages) {
        damagesService.update(damages);

        return "redirect:/showDamageReport?contract_id=" + damages.getContract_id();
    }

    @GetMapping("/showDamageReport")
    public String showDamageReport(Model model, @RequestParam int contract_id) {
        if (damagesService.getDamages(contract_id).isEmpty()) {
            Damages damageReport = new Damages();
            damageReport.setPrice(0.0);
            damageReport.setContract_id(contract_id);
            damagesService.insert(damageReport);
        }

        model.addAttribute("damageReport", damagesService.getDamages(contract_id).get(0));
        return "home/showDamages";
    }
}
