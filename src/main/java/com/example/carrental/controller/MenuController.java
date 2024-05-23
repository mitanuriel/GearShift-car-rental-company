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

    public MenuController(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @GetMapping("/gotoContracts")
    public String gotocontract(){
        return "home/newcontract";
    }

    @GetMapping("/gotomenu")
    public String gotomenu(@CookieValue(required = false) String passwd){
        if (!administratorService.checkCookie(passwd)) {
            return "redirect:/";
        }

        return "home/Menu";
    }
    @GetMapping("/logout")
    public String logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("passwd", null);
        cookie.setMaxAge(0);
        cookie.setPath("/");

        response.addCookie(cookie);

        return "home/index";
    }

    @GetMapping("/gotoshowrepport")
    public String gotoshowrepport(@CookieValue(required = false) String passwd) {
        if (!administratorService.checkCookie(passwd)) {
            return "redirect:/";
        }

        return "home/ShowRepport";
    }
    @GetMapping("/gotoshowcontracts")
    public String gotoshowcontract(){
        return "redirect:/ShowContracts?";
    }

    @GetMapping("/gotoCostumer")
    public String gotocostumer(){
        return "redirect:/" +"Showcostumerlist?";
    }

    @GetMapping("/gotocreatecostumer")
    public String gotocreatecostumer(@CookieValue(required = false) String passwd) {
        if (!administratorService.checkCookie(passwd)) {
            return "redirect:/";
        }
        return "home/newCustomer";
    }
    @GetMapping("/gotocustomerlist")
    public String gotocustomerlist(){
        return "redirect:/" +"Showcostumerlist?";
    }





}
