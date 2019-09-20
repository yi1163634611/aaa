package com.bysj.auto.bean;

import lombok.Data;

@Data
public class Cart {

    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer num;

    private String productName;
    private String productType;
    private Double productPrice;
    private Integer productStock;
    private String productMerchants;
    private String productImg;
}
