package com.example.carrental.model;

import java.time.LocalDate;

public class contractlist {
    private int contract_id;
    private String phone_number;
    private String chassis_number;
    private LocalDate contract_start;
    private LocalDate contract_end;
    private float price;
    public contractlist(){

    }

    public contractlist(int contract_id, String phone_number, String chassis_number, LocalDate contract_start, LocalDate contract_end, float price) {
        this.contract_id = contract_id;
        this.phone_number = phone_number;
        this.chassis_number = chassis_number;
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

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String getChassis_number() {
        return chassis_number;
    }

    public void setChassis_number(String chassis_number) {
        this.chassis_number = chassis_number;
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

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
