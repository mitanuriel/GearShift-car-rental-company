package com.example.carrental.controller;

import com.example.carrental.model.Customer;
import com.example.carrental.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CustomerController {
    @Autowired
    public CustomerService customerService;


    @GetMapping("/newcustomer")
    public String newcustomer(){
        return "home/newCustomer";
    }

    @PostMapping("/addnewCostumer")
    public String insertcustomer(@RequestParam String name, @RequestParam String email, @RequestParam String phone_number, @RequestParam String address){
        customerService.addCustomer(name,email,phone_number,address);
        return "redirect:/" +"gotoCostumer?";

    }

    @GetMapping("/Showcostumerlist")
    public String showcostumerlist(Model model){
        model.addAttribute("getcostumerlist",customerService.getcostumerlist());

        System.out.println(customerService.getcostumerlist());
        return "home/Showcostumers";
    }

    @GetMapping("/editcustomer")
    public String editcustomer(Model model,@RequestParam int customerid ){
        model.addAttribute("customeredit",customerService.getperson(customerid));
        return "home/editcustomer";
    }

    @PostMapping("/Editcustomer")
    public String editCustomer(Model model,@RequestParam int customerid,@RequestParam String name,@RequestParam String email,@RequestParam String phonenumber, @RequestParam String adress){
        customerService.editcustomer(customerid,name,email,phonenumber,adress);
        return "redirect:/" +"Showcostumerlist?";
    }

    @GetMapping("/confirmdeletecustomer")
    public String confirmdelete(Model model,@RequestParam int customerid){
        Customer customer = customerService.getperson(customerid);

        // Add customer object to the model
        model.addAttribute("customer", customer);
        return "home/deletecustomer";
    }

    @PostMapping("/deletecustomer")
    public String deletecustomer(@RequestParam int customerid){
        customerService.delete(customerid);
        return "redirect:/" +"Showcostumerlist?";
    }
}
