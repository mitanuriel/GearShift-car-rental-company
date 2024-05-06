package com.example.carrental.Controller;


import com.example.carrental.Model.Contract;
import com.example.carrental.Repository.ContractRepository;
import com.example.carrental.Service.ContractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ContractController {
    private ContractService contractService;

    @Autowired
    public ContractController(ContractService contractService) {
        this.contractService = contractService;
    }


    @GetMapping("/newcontract")
    private String siteAddContract(){
        return "home/newcontract";
    }

    @PostMapping("/newcontract1")
    private String addContract(Model model,@RequestParam int customer_id, @RequestParam int car_id, @RequestParam LocalDate contract_start, @RequestParam LocalDate contract_end, @RequestParam double price){
        System.out.println("Received request: customer_id=" + customer_id + ", car_id=" + car_id + ", contract_start=" + contract_start + ", contract_end=" + contract_end + ", price=" + price);


        List<Contract> contracts = contractService.getlist();

        Contract contract1 = new Contract();

        boolean isvalid = false;

        try{
            for(int i = 0; i < contracts.size(); i++) {
                contract1 = contracts.get(i);
                if (contract1.getCustomer_id() == customer_id && contract1.getCar_id() == car_id) {
                    isvalid = true;
                    break;
                }
            }
            if(isvalid){
                model.addAttribute("message","Contract exist");
                return "home/newcontract";
            }else {
                model.addAttribute("message", "Contract created successfully");
                contractService.createContract(customer_id,car_id,contract_start,contract_end,price);
            }


        }catch (Exception e){
            System.err.println("Error while creating contract: " + e.getMessage());
            model.addAttribute("message", "Error while creating contract: " + e.getMessage());
        }



        return "home/newcontract";
    }





}
