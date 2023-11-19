package com.example.method2023.Tests;

import com.example.method2023.Dtos.CartItem;
import com.example.method2023.Entity.Item;
import com.example.method2023.Services.ItemService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CartItemTest {

    @Test
    void getTotalPriceSingle() {
        Item item = new Item(1, "Test", "test", "test", 10);
        CartItem cartItem = new CartItem(item);
        int testOverallPrice = cartItem.getTotalPrice();
        assertEquals(10, testOverallPrice);
    }
    @Test
    void getTotalPriceMultiple() {
        Item item = new Item(1, "Test", "test", "test", 10);
        CartItem cartItem = new CartItem(item, 10);

        int testOverallPrice = cartItem.getTotalPrice();

        assertEquals(100, testOverallPrice);
    }

}