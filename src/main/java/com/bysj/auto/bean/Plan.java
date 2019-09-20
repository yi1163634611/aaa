package com.bysj.auto.bean;

import lombok.Data;

@Data
public class Plan {

    private Integer id;
    private String name;
    private String merchants;
    private String img;


    // 从数据库中取出 各个 product 进行计算得到
    private Double price;

    //新增方案时使用
    private Integer userId;
}
