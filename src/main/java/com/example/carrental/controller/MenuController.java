package com.example.carrental.controller;

import com.example.carrental.service.CustomerService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class MenuController {

    @GetMapping("/gotoContracts")
    public String gotocontract(){
        return "home/newcontract";
    }

    @GetMapping("/gotomenu")
    public String gotomenu(){
        return "home/Menu";
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("adminlogin");
        return "home/index";
    }

    @GetMapping("/gotoshowrepport")
    public String gotoshowrepport(){
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
    public String gotocreatecostumer(){
        return "home/newCustomer";
    }
    @GetMapping("/gotocustomerlist")
    public String gotocustomerlist(){
        return "redirect:/" +"Showcostumerlist?";
    }





}
