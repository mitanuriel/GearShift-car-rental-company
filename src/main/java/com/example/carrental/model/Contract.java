package com.example.carrental.model;

import java.time.LocalDate;

public class Contract {
    private int contract_id;
    private int customer_id;
    private int car_id;
    private LocalDate contract_start;
    private LocalDate contract_end;
    private double price;

    public Contract(){

    }
    public Contract(int contract_id, int customer_id, int car_id, LocalDate contract_start, LocalDate contract_end, double price) {
        this.contract_id = contract_id;
        this.customer_id = customer_id;
        this.car_id = car_id;
        this.contract_start = contract_start;
        this.contract_end = contract_end;
        this.price = price;
    }

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public LocalDate getContract_start() {
        return contract_start;
    }

    public void setContract_start(LocalDate contract_start) {
        this.contract_start = contract_start;
    }

    public LocalDate getContract_end() {
        return contract_end;
    }

    public void setContract_end(LocalDate contract_end) {
        this.contract_end = contract_end;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
