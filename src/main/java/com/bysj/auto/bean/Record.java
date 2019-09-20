package com.bysj.auto.bean;

import lombok.Data;

@Data
public class Record {

    private Integer id;
    private Integer userId;
    private Integer productId;
    private Integer num;
    private String createTime;

    private String productName;
}
