package com.example.carrental.Repository;

import com.example.carrental.Model.Contract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class ContractRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;


    public void createContract(int customerId, int carId, LocalDate contractStart, LocalDate contractEnd, double price) {
        String query = "insert into contract (customer_id, car_id, contract_start,contract_end,price)" + "values(?, ?, ?, ?, ?);";
        jdbcTemplate.update(query, customerId,carId,contractStart,contractEnd,price);
    }

    public List<Contract> getlist() {
        String query = "select * from contract";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return jdbcTemplate.query(query,rowMapper);
    }

    public Contract getContract(int contractId) {
        String query = "SELECT * FROM contract WHERE contract_id = ?;";
        RowMapper<Contract> rowMapper = new BeanPropertyRowMapper<>(Contract.class);
        return jdbcTemplate.queryForObject(query, rowMapper, contractId);
    }



    public void updateContract(int contract_id,int custumerId, int carId, LocalDate contractStart, LocalDate contractEnd, double price) {
        String query = "UPDATE contract "
                + "SET customer_id = ?, "
                + "car_id = ?, "
                + "contract_start = ?, "
                + "contract_end = ?, "
                + "price = ? "
                + "WHERE contract_id = ?;";
        jdbcTemplate.update(query,custumerId,carId,contractStart,contractEnd,price,contract_id);
    }
}
