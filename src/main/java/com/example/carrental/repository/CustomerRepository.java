package com.example.carrental.repository;

import com.example.carrental.model.Car;
import com.example.carrental.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Customer> getCustomerid(String phonenumber) {
        String query = "SELECT * FROM customer WHERE phone_number = ?;";
        BeanPropertyRowMapper<Customer> rowMapper = new BeanPropertyRowMapper<>(Customer.class);
        return  jdbcTemplate.query(query, rowMapper, phonenumber);

    }
}
