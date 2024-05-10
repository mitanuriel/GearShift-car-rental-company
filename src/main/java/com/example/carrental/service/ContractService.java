package com.example.carrental.Service;

import com.example.carrental.Model.Contract;
import com.example.carrental.Repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContractService {


    private ContractRepository contractRepository;

    @Autowired
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
}
