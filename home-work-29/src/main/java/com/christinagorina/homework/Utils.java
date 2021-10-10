package com.christinagorina.homework;

public class Utils {
    public static boolean checkStock(String itemName){
        boolean isStock = false;
        if(itemName.equals("whiskey") || itemName.equals("beer")){
            isStock = true;
        }
        return isStock;
    }
}
