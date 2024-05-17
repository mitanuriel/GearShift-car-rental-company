package com.example.carrental.repository;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.carrental.model.CarImage;

@Repository
public class CarImageRepository {
    private JdbcTemplate jdbcTemplate;

    public CarImageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CarImage> getAllImages(int car_id) {
        String query = "SELECT * FROM car_image WHERE car_id = ?;";
        BeanPropertyRowMapper<CarImage> rowMapper = new BeanPropertyRowMapper<>(CarImage.class);
        return jdbcTemplate.query(query, rowMapper, car_id);
    }

    public void insert(CarImage carImage) {
        String query = "INSERT INTO car_image(image, car_id) "
            + "VALUES (?, ?);";
        jdbcTemplate.update(query, carImage.getImage(), carImage.getCar_id());
    }

    public void delete(int car_image_id) {
        String query = "DELETE FROM car_image WHERE car_image_id = ?;";
        jdbcTemplate.update(query, car_image_id);
    }
}
