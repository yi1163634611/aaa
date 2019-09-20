package com.bysj.auto.dao;

import com.bysj.auto.bean.Cart;
import com.bysj.auto.provider.CartProvider;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CartMapper {

    @Select("select ca.id,ca.userId,ca.productId,ca.num ," +
            "pr.name as productName ,pr.type as productType ,pr.merchants as productMerchants, " +
            "pr.price as productPrice , pr.stock as productStock ,pr.img as productImg from cart ca " +
            "join product pr on pr.id = ca.productId where userId = #{userId}")
    List<Cart> select(int userId);


    @Select("select id,userId,productId,num from cart where userId = #{userId} AND productId = #{productId}")
    Cart selectByUserIdAndProductId(HashMap params);

    @InsertProvider(type = CartProvider.class, method = "insert")
    int insert(Cart cart);

    @Delete("DELETE FROM `auto`.`cart` WHERE `id` = #{id}")
    int delete(int id);

    @Delete("DELETE FROM `auto`.`cart` WHERE `userId` = #{userId}")
    int deleteByUserId(int userId);

    @Update("UPDATE `auto`.`cart` SET `num` = #{num} WHERE `id` = #{id} ")
    int update(Cart cart);

}
