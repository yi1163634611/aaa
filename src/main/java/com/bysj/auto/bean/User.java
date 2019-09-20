package com.bysj.auto.bean;

import lombok.Data;

@Data
public class User {


    // tb_user
    private Integer userId;

    private String userName;

    private String password;

    private String phone;

    private String createTime;

    private String email;

    // tb_user_role
    private Integer roleId; // join on

    private String roleName;

    public boolean isAdmin() {
        if (roleName.equalsIgnoreCase("admin"))
            return true;
        else
            return false;
    }

}
