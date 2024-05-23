package com.example.carrental.repository;

import com.example.carrental.model.Administrator;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class AdministratorRepository {
    private JdbcTemplate jdbcTemplate;

    public AdministratorRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Administrator getAdministrator() {
        String query = "select * from administrator WHERE administrator_id = 1;";
        BeanPropertyRowMapper<Administrator> rowMapper = new BeanPropertyRowMapper<>(Administrator.class);
        return jdbcTemplate.queryForObject(query, rowMapper);
    }
}
