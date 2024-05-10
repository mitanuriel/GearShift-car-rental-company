package com.example.carrental.model;

public class Damages {
    private int damages_id;
    private String description;
    private double price;
    private int contract_id;

    public Damages() {
    }

    public int getDamages_id() {
        return damages_id;
    }

    public void setDamages_id(int damages_id) {
        this.damages_id = damages_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getContract_id() {
        return contract_id;
    }

    public void setContract_id(int contract_id) {
        this.contract_id = contract_id;
    }
}
