package com.example.carrental.service;

import com.example.carrental.model.Customer;
import com.example.carrental.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getCustomer(String phonenumber) {
        return customerRepository.getCustomerid(phonenumber);
    }

    public List<Customer> getcostumerlist() {
        return customerRepository.getcustomerlist();
    }

    public void addCustomer(String name, String email, String phoneNumber, String address) {
        customerRepository.addCustomer(name,email,phoneNumber,address);
    }

    public void delete(int customerid) {
        customerRepository.delete(customerid);
    }



    public Customer getperson(int customerid) {
     return customerRepository.getperson(customerid);
    }

    public void editcustomer(int customerid, String name, String email, String phonenumber, String adress) {
        customerRepository.editcustomer(customerid,name,email,phonenumber,adress);
    }
}
