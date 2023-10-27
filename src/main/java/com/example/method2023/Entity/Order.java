package com.example.method2023.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@Entity
@Table(name = "Orders")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    Boolean completed;
    @OneToOne
    private User customer;
    @OneToMany()
    private ArrayList<Item> cart;

    private int cartPrice(){
       return cart.stream().mapToInt(item-> item.getPrice()).sum();
    }


}
