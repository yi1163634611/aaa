package com.bysj.auto.bean;

import lombok.Data;

@Data
public class Product {

    private Integer id;
    private String name;
    private Double price;
    private String merchants;
    private String type;
    private Integer stock;
    private String img;

    private Integer num;
}
