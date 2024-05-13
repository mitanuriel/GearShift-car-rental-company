package com.example.carrental.controller;
import com.example.carrental.model.Car;
import com.example.carrental.model.Contract;
import com.example.carrental.service.CarService;
import com.example.carrental.service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Controller
public class ContractController {
    private ContractService contractService;
    private CarService carService;



    @Autowired
    public ContractController(ContractService contractService, CarService carService) {
        this.contractService = contractService;
        this.carService = carService;
    }


    @GetMapping("/newcontract")
    private String siteAddContract(){
        return "home/newcontract";
    }

    @PostMapping("/newcontract1")
    private String addContract(Model model,@RequestParam int customer_id, @RequestParam int car_id, @RequestParam LocalDate contract_start, @RequestParam LocalDate contract_end){
        List<Contract> contracts = contractService.getlist();

        Car car1 = carService.getCar(car_id);
        Contract contract1 = new Contract();


        Period period = Period.between(contract_start, contract_end);

        int month = period.getMonths();
        int days = period.getDays();

        if(month < 3){
            model.addAttribute("Error","Lejeperioden must be 3 måneder mindst");
            return "home/newcontract";
        }else{
            // Calculate leasing price
            double monthlyPrice = car1.getMonthly_price();
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
                    if (contract1.getCustomer_id() == customer_id && contract1.getCar_id() == car_id && (contract1.getContract_start().isBefore(contract_end) && contract1.getContract_end().isAfter(contract_start)) ||
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
                    contractService.createContract(customer_id,car_id,contract_start,contract_end,totalPrice);
                }
                System.out.println("Received request: customer_id=" + customer_id + ", car_id=" + car_id + ", contract_start=" + contract_start + ", contract_end=" + contract_end + ", price=" + totalPrice);
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
    private String showRepport(Model model,@RequestParam LocalDate start, @RequestParam LocalDate end){

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
    private String validate(Model model,@RequestParam String password){
        if(password.equals("123")){
            return "home/Menu";
        }else{
            return "home/index";
        }

    }

    @GetMapping("/ShowContracts")
    private String showContracts(Model model){
        model.addAttribute("ContractList",contractService.getlist());
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
}
