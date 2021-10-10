package com.christinagorina.homework.domain;

public class Food {

    private String name;
    private final Boolean stock;

    public Food(String name, Boolean stock) {
        this.name = name;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public void setName(String newName) {
        name = newName;
    }

    public Boolean getStock() {
        return stock;
    }

}
