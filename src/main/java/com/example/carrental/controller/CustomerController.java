package com.example.carrental.controller;

import com.example.carrental.model.Customer;
import com.example.carrental.service.CustomerService;
import com.example.carrental.service.AdministratorService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CustomerController {
    private CustomerService customerService;
    private AdministratorService administratorService;
    //lavet af Hung
    
    public CustomerController(CustomerService customerService, AdministratorService administratorService) {
        this.customerService = customerService;
        this.administratorService = administratorService;
    }
    //lavet af Hung
    @GetMapping("/newcustomer")
    public String newcustomer(@CookieValue(required = false) String passwd) {
        if (!administratorService.checkCookie(passwd)) {
            return "redirect:/";
        }

        return "home/newCustomer";
    }
    //lavet af Hung
    @PostMapping("/addnewCostumer")
    public String insertcustomer(@RequestParam String name, @RequestParam String email, @RequestParam String phone_number, @RequestParam String address){
        customerService.addCustomer(name,email,phone_number,address);
        return "redirect:/" +"Showcostumerlist?";

    }
    //lavet af Hung
    @GetMapping("/Showcostumerlist")
    public String showcostumerlist(Model model, @CookieValue(required = false) String passwd) {
        if (!administratorService.checkCookie(passwd)) {
            return "redirect:/";
        }

        model.addAttribute("getcostumerlist",customerService.getcostumerlist());

        System.out.println(customerService.getcostumerlist());
        return "home/Showcostumers";
    }
    //lavet af Hung
    @GetMapping("/editcustomer")
    public String editcustomer(Model model,@RequestParam int customerid, @CookieValue(required = false) String passwd) {
        if (!administratorService.checkCookie(passwd)) {
            return "redirect:/";
        }

        model.addAttribute("customeredit",customerService.getperson(customerid));
        return "home/editcustomer";
    }
    //lavet af Hung
    @PostMapping("/Editcustomer")
    public String editCustomer(Model model,@RequestParam int customerid,@RequestParam String name,@RequestParam String email,@RequestParam String phonenumber, @RequestParam String adress){
        customerService.editcustomer(customerid,name,email,phonenumber,adress);
        return "redirect:/" +"Showcostumerlist?";
    }
    //lavet af Hung
    @GetMapping("/confirmdeletecustomer")
    public String confirmdelete(Model model,@RequestParam int customerid, @CookieValue(required = false) String passwd) {
        if (!administratorService.checkCookie(passwd)) {
            return "redirect:/";
        }

        Customer customer = customerService.getperson(customerid);

        // Add customer object to the model
        model.addAttribute("customer", customer);
        return "home/deletecustomer";
    }
    //lavet af Hung
    @PostMapping("/deletecustomer")
    public String deletecustomer(@RequestParam int customerid){
        customerService.delete(customerid);
        return "redirect:/" +"Showcostumerlist?";
    }
}
