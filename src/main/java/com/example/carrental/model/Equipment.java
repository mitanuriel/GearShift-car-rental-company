package com.example.carrental.model;

public class Equipment {
    private int equipment_id;
    private String description;
    private int car_id;
    //lavet af Oliver
    public Equipment() {
    }

    public Equipment(int car_id) {
        this.car_id = car_id;
    }

    public int getEquipment_id() {
        return equipment_id;
    }

    public void setEquipment_id(int equipment_id) {
        this.equipment_id = equipment_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }
}
