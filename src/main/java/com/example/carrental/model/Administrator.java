package com.example.carrental.model;

public class Administrator {
    private String password;

    public Administrator(){

    }
    //lavet af Oliver
    public Administrator(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }




}
