package com.bysj.auto.controller;

import com.bysj.auto.bean.User;
import com.bysj.auto.common.ListResponseEntity;
import com.bysj.auto.common.ResponseEntity;
import com.bysj.auto.service.UserService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    @PostMapping
    @ResponseBody
    public ResponseEntity addUser(
            @RequestBody User user
    ) {
        ResponseEntity responseEntity = new ResponseEntity();

        if (user.getRoleId() == null) {
            user.setRoleId(2);
        }
        int result = userService.addUser(user);

        responseEntity.setData(result);

        return responseEntity;
    }

    @DeleteMapping
    @ResponseBody
    public ResponseEntity deleteUser(
            @RequestParam(value = "userId") Integer userId
    ) {
        ResponseEntity responseEntity = new ResponseEntity();

        int result = userService.deleteUser(userId);

        responseEntity.setData(result);

        return responseEntity;
    }


    @PutMapping
    @ResponseBody
    public ResponseEntity updateUser(
            @RequestBody User inputUser
    ) {
        ResponseEntity responseEntity = new ResponseEntity();

        int userId = inputUser.getUserId();

        User user = userService.getUserById(userId);

        if (user == null) {
            responseEntity.setCode(4000);
        } else {
            if (inputUser.getUserName() != null) user.setUserName(inputUser.getUserName());
            if (inputUser.getPassword() != null) user.setPassword(inputUser.getPassword());
            if (inputUser.getPhone() != null) user.setPhone(inputUser.getPhone());
            if (inputUser.getEmail() != null) user.setEmail(inputUser.getEmail());
            if (inputUser.getRoleId() != null) user.setRoleId(inputUser.getRoleId());
            int result = userService.updateUser(user);
            responseEntity.setData(result);
        }
        return responseEntity;
    }


    @GetMapping
    @ResponseBody
    public ListResponseEntity getUser(
            @RequestParam(value = "page", defaultValue = "1") int page,
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "userId", required = false) Integer userId,
            @RequestParam(value = "userName", required = false) String userName,
            @RequestParam(value = "password", required = false) String password,
            @RequestParam(value = "phone", required = false) String phone,
            @RequestParam(value = "createTimeHead", required = false) String createTimeHead,
            @RequestParam(value = "createTimeEnd", required = false) String createTimeEnd,
            @RequestParam(value = "email", required = false) String email,
            @RequestParam(value = "roleId", required = false) Integer roleId
    ) {
        ListResponseEntity responseEntity = new ListResponseEntity();

        HashMap<String, Object> params = new HashMap<String, Object>() {
            {
                if (userId != null) put("userId", userId);
                if (userName != null) put("userName", userName);
                if (password != null) put("password", password);
                if (phone != null) put("phone", phone);
                if (createTimeHead != null) put("createTimeHead", createTimeHead);
                if (createTimeEnd != null) put("createTimeEnd", createTimeEnd);
                if (email != null) put("email", email);
                if (roleId != null) put("roleId", roleId);
            }
        };

        PageHelper.startPage(page, size);

        List<User> list = userService.getUserByConditions(params);

        responseEntity.setList(list);

        return responseEntity;
    }


}
