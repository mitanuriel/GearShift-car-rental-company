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

    public ContractService(ContractRepository contractrepository){
        this.contractRepository = contractrepository;

    }


    public void createContract(int customerId, int carId, LocalDate contractStart, LocalDate contractEnd, double price) {
        contractRepository.createContract(customerId,carId,contractStart,contractEnd,price);
    }

    public List<Contract> getlist() {
        return contractRepository.getlist();
    }

    public Contract getContract(int contractId) {
        return contractRepository.getContract(contractId);
    }


    public void updateContract(int contract_id,int custumerId, int carId, LocalDate contractStart, LocalDate contractEnd, double price) {
        contractRepository.updateContract(contract_id,custumerId,carId,contractStart,contractEnd,price);
    }

    public void delete(int contractId) {
        contractRepository.deletecontract(contractId);
    }


    public List<contractlist> getlistinfo() {
        return contractRepository.getlistinfo();
    }
}
