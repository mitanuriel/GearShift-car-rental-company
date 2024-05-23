package com.example.carrental.repository;

import com.example.carrental.model.Damages;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DamagesRepository {
    private JdbcTemplate jdbcTemplate;

    public DamagesRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Damages> getDamages(int contract_id) {
        String query = "SELECT * FROM damages WHERE contract_id = ?;";
        BeanPropertyRowMapper<Damages> rowMapper = new BeanPropertyRowMapper<>(Damages.class);
        return jdbcTemplate.query(query, rowMapper, contract_id);
    }

    public void insert(Damages damages) {
        String query = "INSERT INTO damages(description, price, contract_id) "
            + "VALUES (?, ?, ?);";
        jdbcTemplate.update(query, damages.getDescription(), damages.getPrice(), damages.getContract_id());
    }

    public void delete(int damages_id) {
        String query = "DELETE FROM damages WHERE damages_id = ?;";
        jdbcTemplate.update(query, damages_id);
    }

    public void update(Damages damages) {
        String query = "UPDATE damages "
                + "SET description = ?, price = ?, contract_id = ? "
                + "WHERE damages_id = ?;";
        jdbcTemplate.update(query, damages.getDescription(), damages.getPrice(), damages.getContract_id(), damages.getDamages_id());
    }
}