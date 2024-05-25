package com.example.carrental.repository;

import com.example.carrental.model.Customer;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {
    private JdbcTemplate jdbcTemplate;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> getCustomerid(String phonenumber) {
        String query = "SELECT * FROM customer WHERE phone_number = ?;";
        BeanPropertyRowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return  jdbcTemplate.query(query, rowMapper, phonenumber);

    }

    public List<Customer> getcustomerlist() {
        String query = "SELECT * FROM customer;";
        BeanPropertyRowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return jdbcTemplate.query(query, rowMapper);
    }


    public void addCustomer(String name, String email, String phoneNumber, String address) {
        String query = "INSERT INTO customer (name, email, phone_number, address) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(query,name,email,phoneNumber,address);
    }


    public void delete(int customerid) {
        String query = "DELETE FROM customer WHERE customer_id = ?";
        jdbcTemplate.update(query, customerid);
    }

    public Customer getperson(int customerid) {
        String query = "SELECT * FROM customer WHERE customer_id = ?;";
        RowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return jdbcTemplate.queryForObject(query, rowMapper, customerid);
    }

    public void editcustomer(int customerid, String name, String email, String phonenumber, String address) {
        String query = "UPDATE customer "
                + "SET name = ?, "
                + "email = ?, "
                + "phone_number = ?, "
                + "address = ? "
                + "WHERE customer_id = ?;";
        jdbcTemplate.update(query, name, email, phonenumber, address, customerid);
    }
}
