package com.bysj.auto.provider;

import com.bysj.auto.bean.Cart;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public class CartProvider {

    public String insert(Cart cart) {
        return new SQL() {{
            INSERT_INTO("cart");
            VALUES("userId,productId,num", "#{userId},#{productId},#{num}");
        }}.toString();
    }
}
