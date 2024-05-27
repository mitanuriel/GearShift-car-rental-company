package com.example.carrental.repository;

import com.example.carrental.model.Equipment;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class EquipmentRepository {
    private JdbcTemplate jdbcTemplate;

    public EquipmentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    //lavet af Oliver
    public List<Equipment> getAllEquipment(int car_id) {
        String query = "SELECT * FROM equipment WHERE car_id = ?;";
        BeanPropertyRowMapper<Equipment> rowMapper = new BeanPropertyRowMapper<>(Equipment.class);
        return jdbcTemplate.query(query, rowMapper, car_id);
    }
    //lavet af Oliver
    public void insert(Equipment equipment) {
        String query = "INSERT INTO equipment(description, car_id) "
            + "VALUES (?, ?);";
        jdbcTemplate.update(query, equipment.getDescription(), equipment.getCar_id());
    }
    //lavet af Oliver
    public void delete(int equipment_id) {
        String query = "DELETE FROM equipment WHERE equipment_id = ?;";
        jdbcTemplate.update(query, equipment_id);
    }
}