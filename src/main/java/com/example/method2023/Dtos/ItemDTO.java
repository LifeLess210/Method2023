package com.example.method2023.Dtos;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDTO {
    private Integer id;
    private String name;
    private String description;
    private String itemPicture;
    private int price;
}
