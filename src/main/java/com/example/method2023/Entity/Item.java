package com.example.method2023.Entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Items")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String description;
    private String itemPicture;
    private int price;
}
