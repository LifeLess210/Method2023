package com.example.method2023.Dtos;

import com.example.method2023.Entity.Item;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Item item;
    private int quantity;

    public CartItem(Item item) {
        this.item = item;
        this.quantity = 1; // Kezdetben minden elemet egyszer adunk hozzá a kosárhoz
    }

    public Item getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return item.getPrice() * quantity;
    }
}
