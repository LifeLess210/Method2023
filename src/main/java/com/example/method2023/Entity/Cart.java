package com.example.method2023.Entity;

import com.example.method2023.Dtos.CartItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cart {
    ArrayList<CartItem> cart = new ArrayList<>();
    public void addItem(CartItem cartItem){
        cart.add(cartItem);
    }

    public int sum(){
        return cart.stream().mapToInt(CartItem::getTotalPrice).sum();
    }
}
