package com.example.method2023.Services;

import com.example.method2023.Dtos.ItemDTO;
import com.example.method2023.Entity.Item;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


public interface ItemService {
    void saveItem(ItemDTO itemDTO);

    ArrayList<Item> getAllItems();
}
