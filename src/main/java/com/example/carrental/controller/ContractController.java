package com.example.carrental.controller;
import com.example.carrental.model.Car;
import com.example.carrental.model.Contract;
import com.example.carrental.model.Customer;
import com.example.carrental.model.password;
import com.example.carrental.service.CarService;
import com.example.carrental.service.ContractService;
import com.example.carrental.service.CustomerService;
import com.example.carrental.service.PasswordService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ContractController {
    private ContractService contractService;
    private CarService carService;
    private CustomerService customerService;

    private PasswordService passwordService;


    @Autowired
    public ContractController(ContractService contractService, CarService carService, CustomerService customerService,PasswordService passwordService) {
        this.contractService = contractService;
        this.carService = carService;
        this.customerService = customerService;
        this.passwordService = passwordService;
    }


    @GetMapping("/newcontract")
    private String siteAddContract(){
        return "home/newcontract";
    }

    @PostMapping("/newcontract1")
    private String addContract(Model model,@RequestParam String phonenumber, @RequestParam String chassisnumber, @RequestParam LocalDate contract_start, @RequestParam LocalDate contract_end){
        List<Contract> contracts = contractService.getlist();

        Contract contract1 = new Contract();

        List<Customer> customer = customerService.getCustomer(phonenumber);
        if (customer.isEmpty()) {
            model.addAttribute("Error", "Customer with the provided phone number does not exist.");
            return "home/newcontract";
        }
        List<Car> car1 = carService.getCar1(chassisnumber);
        if (car1.isEmpty()) {
            model.addAttribute("Error", "Car with the provided chassis number does not exist.");
            return "home/newcontract";
        }


        int carid = car1.get(0).getCar_id();
        int cusomerid = customer.get(0).getCustomer_id();

        System.out.println("carid :" + carid + "  cusomerid : " + cusomerid);

        Period period = Period.between(contract_start, contract_end);

        int month = period.getMonths();
        int days = period.getDays();


        if(month < 3){
            model.addAttribute("Error","The lease period must be at least 3 months.");
            return "home/newcontract";
        }else{
            // Calculate leasing price
            double monthlyPrice = car1.get(0).getMonthly_price();
            double totalPrice = monthlyPrice * month;
            if (days > 0) {
                double dailyPrice = (double) monthlyPrice / 30;
                double extraDaysPrice = dailyPrice * days;
                totalPrice += Math.round(extraDaysPrice);
            }

            boolean isvalid = false;
            try{
                for(int i = 0; i < contracts.size(); i++) {
                    contract1 = contracts.get(i);
                    if (contract1.getCustomer_id() == cusomerid && contract1.getCar_id() == carid && (contract1.getContract_start().isBefore(contract_end) && contract1.getContract_end().isAfter(contract_start)) ||
                            (contract1.getContract_start().equals(contract_start) && contract1.getContract_end().equals(contract_end))) {

                        isvalid = true;
                        break;
                    }
                }
                if(isvalid){
                    model.addAttribute("message","Contract exist/cantbemade");
                    return "home/newcontract";
                }else {
                    model.addAttribute("message", "Contract created successfully" + "the tolal price will be = " + totalPrice);
                    model.addAttribute("totalprice",totalPrice);
                    contractService.createContract(cusomerid,carid,contract_start,contract_end,totalPrice);
                }
                System.out.println("Received request: customer_id=" + customer + ", car_id=" + carid + ", contract_start=" + contract_start + ", contract_end=" + contract_end + ", price=" + totalPrice);
            }catch (Exception e){
                System.err.println("Error while creating contract: " + e.getMessage());
                model.addAttribute("message", "Error while creating contract: " + e.getMessage());
            }
            return "home/newcontract";

        }

        //kan testes mere,

    }

    @GetMapping("/showprice")
    private String Showprice(Model model){

        List<Contract> contracts = contractService.getlist();
//dato fra price så man vælger en dato til slutdato og får en pris
        double price = 0;
        for (int i = 0; i < contracts.size(); i++) {
             price += contracts.get(i).getPrice();
        }
        model.addAttribute("price",price);

        return "home/newcontract";
    }

    @GetMapping("/ShowRepport")
    private String showRepport(Model model, @RequestParam LocalDate start, @RequestParam LocalDate end,HttpSession httpSession){

        if (!passwordService.checkSession(httpSession)){
            return "redirect:/";
        }


        List<Contract> contracts = contractService.getlist();
        Contract contract1 = new Contract();

        double price = 0;
        for(int i = 0; i < contracts.size(); i++) {
            contract1 = contracts.get(i);
            if (!contract1.getContract_start().isBefore(start) && !contract1.getContract_end().isAfter(end)) {
                price += contract1.getPrice();
            }
        }
        model.addAttribute("timeprice","this period you got " + start + " to " + end + " = "+price +"kr.");
        return "home/ShowRepport";
    }

    @PostMapping("/validate")
    private String validate(Model model,@RequestParam String logind,HttpSession session){
        boolean isvalid = false;
        List<password> passwords = passwordService.getpasswordlist();
        password password1 = new password();

        for (int i = 0; i < passwords.size(); i++) {
            password1 = passwords.get(i);
            if(password1.getPassword().equals(logind)){
                isvalid = true;
                break;
            }

        }
        if(isvalid){
            model.addAttribute("logind");
            session.setAttribute("adminlogin", password1);
            return "home/Menu";
        }else{
            return "home/index";
        }

    }

    @GetMapping("/ShowContracts")
    private String showContracts(Model model){
        model.addAttribute("ContractList",contractService.getlistinfo());
        return "home/Contracts";
    }

    @GetMapping("/Editcontract")
    private String editcontract(Model model, @RequestParam int contract_id){
        model.addAttribute("contractedit",contractService.getContract(contract_id));
        return "home/EditContract";
    }

    @PostMapping("/updateContract")
    private String updatecontract(@RequestParam int contract_id,@RequestParam int custumer_id,@RequestParam int car_id,@RequestParam LocalDate contract_start ,@RequestParam LocalDate contract_end , @RequestParam double price){
        contractService.updateContract(contract_id,custumer_id,car_id,contract_start,contract_end,price);
        return "redirect:/" + "ShowContracts?";
    }

    @GetMapping("/comfirmDelete")
    private String comfirmDelete(Model model,@RequestParam int contract_id){
        model.addAttribute("contractid",contract_id);
        model.addAttribute(contractService.getContract(contract_id));
        return "home/comfirmdelete";
    }
    @PostMapping("/delete")
    private String delete(@RequestParam int contract_id){
        contractService.delete(contract_id);
        return "redirect:/" + "ShowContracts?";
    }



}
