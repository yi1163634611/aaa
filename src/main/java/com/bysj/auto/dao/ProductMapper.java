package com.bysj.auto.dao;

import com.bysj.auto.bean.Product;
import com.bysj.auto.provider.ProductProvider;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface ProductMapper {


    @SelectProvider(type = ProductProvider.class, method = "select")
    List<Product> select(HashMap params);

    @SelectProvider(type = ProductProvider.class, method = "selectById")
    Product selectById(HashMap params);

    @Update("UPDATE `auto`.`product` SET `name` = #{name},`price` = #{price},`stock` = #{stock},`img` = #{img} WHERE `id` = #{id}")
    int update(Product product);

    @Insert("INSERT INTO `auto`.`product`(`name`, `price`, `merchants`, `type`, `stock`, `img`) VALUES (#{name}, #{price}, #{merchants}, #{type}, #{stock}, #{img});")
    int insert(Product product);

}
