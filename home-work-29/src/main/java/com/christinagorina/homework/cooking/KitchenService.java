package com.christinagorina.homework.cooking;

import com.christinagorina.homework.Utils;
import com.christinagorina.homework.domain.Food;
import com.christinagorina.homework.domain.OrderItem;
import org.springframework.stereotype.Service;

@Service
public class KitchenService {

    public Food cook(OrderItem orderItem) throws Exception {
        String itemName = orderItem.getItemName();
        System.out.println("Cooking kitchen " + itemName);
        boolean isStock = Utils.checkStock(itemName);
        System.out.println("Cooking kitchen " + orderItem.getItemName() + " done");
        return new Food(orderItem.getItemName(), isStock);
    }
}
