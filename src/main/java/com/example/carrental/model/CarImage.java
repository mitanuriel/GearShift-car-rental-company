package com.example.carrental.model;

import java.util.Base64;

public class CarImage {
    private int car_image_id;
    private byte[] image;
    private int car_id;

    public CarImage() {
    }

    public CarImage(byte[] image, int car_id) {
        this.image = image;
        this.car_id = car_id;
    }

    public String encodeImageToBase64String() {
        return Base64.getEncoder().encodeToString(image);
    }

    public void decodeBase64StringToImage(String base64String) {
        setImage(Base64.getDecoder().decode(base64String));
    }

    public int getCar_image_id() {
        return car_image_id;
    }

    public void setCar_image_id(int car_image_id) {
        this.car_image_id = car_image_id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }
}
