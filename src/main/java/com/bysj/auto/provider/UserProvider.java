package com.bysj.auto.provider;

import com.bysj.auto.bean.User;
import org.apache.ibatis.jdbc.SQL;

import java.util.HashMap;

public class UserProvider {


    public String select(HashMap params) {
        return new SQL() {{
            SELECT("u.userId , " +
                    "u.userName , " +
                    "u.password , " +
                    "u.phone , " +
                    "u.createTime , " +
                    "u.email , " +
                    "r.roleId , " +
                    "r.roleName ");
            FROM("tb_user u ");
            INNER_JOIN("tb_user_role r on u.roleId = r.roleId");
            if (params.containsKey("userId")) {
                WHERE("u.userId = #{userId}");
            }
            if (params.containsKey("userName")) {
                WHERE("u.userName Like \"%\"#{userName}\"%\"");
            }
            if (params.containsKey("userNameEqual")) {
                WHERE("u.userName = #{userNameEqual}");
            }
            if (params.containsKey("password")) {
                WHERE("u.password = #{password}");
            }
            if (params.containsKey("phone")) {
                WHERE("u.phone = #{phone}");
            }
            if (params.containsKey("createTimeHead")) {
                WHERE("u.createTime > #{createTimeHead}");
            }
            if (params.containsKey("createTimeEnd")) {
                WHERE("u.createTime < #{createTimeEnd}");
            }
            if (params.containsKey("email")) {
                WHERE("u.email = #{email}");
            }
            if (params.containsKey("roleId")) {
                WHERE("r.roleId = #{roleId}");
            }
        }}.toString();
    }

    public String insert(User user) {
        return new SQL() {{
            INSERT_INTO("tb_user");
            // userId 自增
            VALUES("userName , password , phone , email  , roleId ",
                    "#{userName} , #{password} , #{phone} , #{email} , #{roleId} ");
        }}.toString();
    }

    public String delete(HashMap params) {
        return new SQL() {{
            DELETE_FROM("tb_user");
            WHERE("userId=#{userId}");
        }}.toString();
    }

    public String update(User user) {
        return new SQL() {{
            UPDATE("tb_user");
            // userId 不可更改
            SET("userName = #{userName}");
            SET("password = #{password}");
            SET("phone = #{phone}");
            // createTime 不可更改
            SET("email = #{email}");
            SET("roleId = #{roleId}");
            WHERE("userId = #{userId}");
        }}.toString();
    }

}
