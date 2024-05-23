package com.example.carrental.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.carrental.service.AdministratorService;

@Controller
public class HomeController {
    private AdministratorService administratorService;

    public HomeController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping("/")
    public String index(@CookieValue(required = false) String passwd) {
        if (administratorService.checkCookie(passwd)) {
            return "redirect:/gotomenu";
        }

        return "home/index";
    }
}
