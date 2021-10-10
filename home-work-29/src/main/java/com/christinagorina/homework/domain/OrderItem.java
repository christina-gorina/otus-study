package com.christinagorina.homework.domain;

public class OrderItem {

    private final String itemName;
    private final Boolean forBar;

    public OrderItem(String itemName, Boolean forBar) {
        this.itemName = itemName;
        this.forBar = forBar;
    }

    public String getItemName() {
        return itemName;
    }

    public Boolean getForBar() {
        return forBar;
    }

}
