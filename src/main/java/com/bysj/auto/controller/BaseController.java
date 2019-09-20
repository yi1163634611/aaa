package com.bysj.auto.controller;

import com.bysj.auto.bean.User;
import com.bysj.auto.util.UserThreadLocal;


public class BaseController {

    public synchronized User getUser() {
        return UserThreadLocal.get();
    }

    /**
     * @Description: 登陆状态是否有效
     * @Param: []
     * @return: boolean
     */
    public boolean isLogin() {
        User user = getUser();
        if (user == null) {
            return false;
        } else {
            return true;
        }
    }


}
