package com.example.carrental.controller;

import org.springframework.stereotype.Controller;
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
    public String logout(){
        return "home/index";
    }

    @GetMapping("/gotoshowrepport")
    public String gotoshowrepport(){
        return "home/ShowRepport";
    }


}
