package com.example.carrental.service;

import com.example.carrental.model.Customer;
import com.example.carrental.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;
    //lavet af Hung
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }
    //lavet af Hung
    public List<Customer> getCustomer(String phonenumber) {
        return customerRepository.getCustomerid(phonenumber);
    }
    //lavet af Hung
    public List<Customer> getcostumerlist() {
        return customerRepository.getcustomerlist();
    }
    //lavet af Hung
    public void addCustomer(String name, String email, String phoneNumber, String address) {
        customerRepository.addCustomer(name,email,phoneNumber,address);
    }
    //lavet af Hung
    public void delete(int customerid) {
        customerRepository.delete(customerid);
    }


    //lavet af Hung
    public Customer getperson(int customerid) {
     return customerRepository.getperson(customerid);
    }
    //lavet af Hung
    public void editcustomer(int customerid, String name, String email, String phonenumber, String adress) {
        customerRepository.editcustomer(customerid,name,email,phonenumber,adress);
    }
}
