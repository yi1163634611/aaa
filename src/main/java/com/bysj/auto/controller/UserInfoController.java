package com.bysj.auto.controller;

import com.bysj.auto.bean.User;
import com.bysj.auto.common.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 获取登陆的用户信息，用于权限判断等
 * 备用
 **/
@Controller
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController {


    @ResponseBody
    @GetMapping
    public ResponseEntity<User> getUserInfo() {
        ResponseEntity<User> responseEntity = new ResponseEntity<>();
        responseEntity.setData(getUser());
        return responseEntity;
    }

}
