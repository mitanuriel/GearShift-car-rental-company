package com.example.carrental.repository;

import com.example.carrental.model.Contract;
import com.example.carrental.model.password;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PasswordRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<password> getpasswordlist() {
        String query = "select * from passwords";
        RowMapper<password> rowMapper = new BeanPropertyRowMapper<>(password.class);
        return jdbcTemplate.query(query,rowMapper);
    }
}
