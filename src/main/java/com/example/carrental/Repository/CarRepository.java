package com.example.carrental.repository;

import com.example.carrental.model.Car;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CarRepository {
    JdbcTemplate jdbcTemplate;

    public CarRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Car> getAllCars() {
        String query = "SELECT * FROM car;";
        BeanPropertyRowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.query(query, rowMapper);
    }

    public Car getCar(int car_id) {
        String query = "SELECT * FROM car WHERE car_id = ?;";
        BeanPropertyRowMapper<Car> rowMapper = new BeanPropertyRowMapper<>(Car.class);
        return jdbcTemplate.queryForObject(query, rowMapper, car_id);
    }

    public void insert(Car car) {
        String query = "INSERT INTO car(model, monthly_price, brand, chassis_number, co2_emissions, equipment_level, state) "
            + "VALUES (?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, car.getModel(), car.getMonthly_price(), car.getBrand(), car.getChassis_number(), car.getCo2_emissions(), car.getEquipment_level(), car.getState());
    }

    public void delete(int car_id) {
        String query = "DELETE FROM car WHERE car_id = ?;";
        jdbcTemplate.update(query, car_id);
    }

    public void update(Car car) {
        String query = "UPDATE car "
                + "SET model = ?, monthly_price = ?, brand = ?, chassis_number = ?, co2_emissions = ?, equipment_level = ?, state = ? "
                + "WHERE car_id = ?;";
        jdbcTemplate.update(query, car.getModel(), car.getMonthly_price(), car.getBrand(), car.getChassis_number(), car.getCo2_emissions(), car.getEquipment_level(), car.getState(), car.getCar_id());
    }
}