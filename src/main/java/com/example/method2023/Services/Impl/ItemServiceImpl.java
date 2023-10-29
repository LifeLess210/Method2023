package com.example.method2023.Services.Impl;

import com.example.method2023.Dtos.ItemDTO;
import com.example.method2023.Entity.Item;
import com.example.method2023.Repos.ItemRepo;
import com.example.method2023.Services.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Override
    public void saveItem(ItemDTO itemDTO) {
        itemRepo.save(convertItemDTOToItem(itemDTO));
    }

    @Override
    public ArrayList<Item> getAllItems() {
        return (ArrayList<Item>) itemRepo.findAll();
    }

    private Item convertItemDTOToItem(ItemDTO itemDTO){
        return Item.builder()
                .itemPicture(itemDTO.getItemPicture())
                .description(itemDTO.getDescription())
                .price(itemDTO.getPrice())
                .name(itemDTO.getName())
                .build();
    }
}
