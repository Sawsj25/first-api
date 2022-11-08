package com.example.testgradle;

public class MyModel {
    String name= "";
    String Number = "";

    public MyModel(String name, String number) {
        this.name = name;
        Number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }
}
