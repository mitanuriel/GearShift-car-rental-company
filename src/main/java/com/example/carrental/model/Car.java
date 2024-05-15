package com.example.carrental.model;

import java.util.Base64;

public class Car {
    private int car_id;
    private String model;
    private double monthly_price;
    private String brand;

    private String license_plate;
    private String chassis_number;
    private String co2_emissions;
    private String equipment_level;
    private String state;
    private byte[] image;

    public Car() {
    }

    public String getLicense_plate() {
        return license_plate;
    }

    public void setLicense_plate(String license_plate) {
        this.license_plate = license_plate;
    }

    public String encodeImageToBase64String() {
        return Base64.getEncoder().encodeToString(image);
    }

    public void decodeBase64StringToImage(String base64String) {
        setImage(Base64.getDecoder().decode(base64String));
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public double getMonthly_price() {
        return monthly_price;
    }

    public void setMonthly_price(double monthly_price) {
        this.monthly_price = monthly_price;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getChassis_number() {
        return chassis_number;
    }

    public void setChassis_number(String chassis_number) {
        this.chassis_number = chassis_number;
    }

    public String getCo2_emissions() {
        return co2_emissions;
    }

    public void setCo2_emissions(String co2_emissions) {
        this.co2_emissions = co2_emissions;
    }

    public String getEquipment_level() {
        return equipment_level;
    }

    public void setEquipment_level(String equipment_level) {
        this.equipment_level = equipment_level;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }


}