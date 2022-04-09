package com.narel.online_store.model;

import lombok.Data;


import javax.persistence.*;

@Data

@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String name;

    private String filename;

    private double price;

    private String cpu;


}
