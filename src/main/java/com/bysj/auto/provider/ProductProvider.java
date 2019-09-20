package com.bysj.auto.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;

public class ProductProvider {

    public String select(HashMap params) {
        return new SQL() {{
            SELECT("pr.id,pr.name,pr.price,pr.merchants,pr.type,pr.stock,pr.img ");
            FROM("product pr");
            if (params.containsKey("planId")) {
                SELECT("re.num as num");
                INNER_JOIN("relation re on pr.id = re.productId");
                INNER_JOIN("plan pl on pl.id = re.planId");
            }
            if (params.containsKey("type")) {
                WHERE("type=#{type}");
            }
            if (params.containsKey("id")) {
                WHERE("id=#{id}");
            }
            if (params.containsKey("planId")) {
                WHERE("pl.id = #{planId}");
            }
        }}.toString();
    }

    public String selectById(HashMap params) {
        return new SQL() {{
            SELECT("id,name,price,merchants,type,stock,img");
            FROM("product");
            if (params.containsKey("id")) {
                WHERE("id=#{id}");
            }
        }}.toString();
    }

}
