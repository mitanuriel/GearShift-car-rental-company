package com.example.carrental.service;

import com.example.carrental.model.Contract;
import com.example.carrental.model.contractlist;
import com.example.carrental.repository.ContractRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContractService {
    private ContractRepository contractRepository;
    //lavet af Hung
    public ContractService(ContractRepository contractrepository){
        this.contractRepository = contractrepository;

    }

    //lavet af Hung
    public void createContract(int customerId, int carId, LocalDate contractStart, LocalDate contractEnd, double price) {
        contractRepository.createContract(customerId,carId,contractStart,contractEnd,price);
    }
    //lavet af Hung
    public List<Contract> getlist() {
        return contractRepository.getlist();
    }
    //lavet af Hung
    public Contract getContract(int contractId) {
        return contractRepository.getContract(contractId);
    }
//lavet af Hung

    public void updateContract(int contract_id,int custumerId, int carId, LocalDate contractStart, LocalDate contractEnd, double price) {
        contractRepository.updateContract(contract_id,custumerId,carId,contractStart,contractEnd,price);
    }
    //lavet af Hung
    public void delete(int contractId) {
        contractRepository.deletecontract(contractId);
    }

    //lavet af Hung
    public List<contractlist> getlistinfo() {
        return contractRepository.getlistinfo();
    }
}
