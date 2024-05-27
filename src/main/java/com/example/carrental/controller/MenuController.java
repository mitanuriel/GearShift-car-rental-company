package com.example.carrental.controller;

import com.example.carrental.service.AdministratorService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
    private AdministratorService administratorService;
    //lavet af Hung

    public MenuController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }
    //lavet af Hung
    @GetMapping("/gotoContracts")
    public String gotocontract(@CookieValue(required = false) String passwd){
        if (!administratorService.checkCookie(passwd)) {
            return "redirect:/";
        }
        
        return "home/newcontract";
    }
    //lavet af Hung
    @GetMapping("/gotomenu")
    public String gotomenu(@CookieValue(required = false) String passwd){
        if (!administratorService.checkCookie(passwd)) {
            return "redirect:/";
        }

        return "home/Menu";
    }
    //lavet af Hung
    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("passwd", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);

        return "home/index";
    }
    //lavet af Hung
    @GetMapping("/gotoshowrepport")
    public String gotoshowrepport(@CookieValue(required = false) String passwd) {
        if (!administratorService.checkCookie(passwd)) {
            return "redirect:/";
        }

        return "home/ShowRepport";
    }
    //lavet af Hung
    @GetMapping("/gotoshowcontracts")
    public String gotoshowcontract(){
        return "redirect:/ShowContracts?";
    }
    //lavet af Hung
    @GetMapping("/gotoCostumer")
    public String gotocostumer(){
        return "redirect:/" +"Showcostumerlist?";
    }
    //lavet af Hung
    @GetMapping("/gotocreatecostumer")
    public String gotocreatecostumer(@CookieValue(required = false) String passwd) {
        if (!administratorService.checkCookie(passwd)) {
            return "redirect:/";
        }
        return "home/newCustomer";
    }
    //lavet af Hung
    @GetMapping("/gotocustomerlist")
    public String gotocustomerlist(){
        return "redirect:/" +"Showcostumerlist?";
    }





}
