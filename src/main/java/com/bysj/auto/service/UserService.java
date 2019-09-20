package com.bysj.auto.service;

import com.bysj.auto.bean.User;
import com.bysj.auto.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
public class UserService {


    @Autowired
    UserMapper userMapper;

    public List<User> getUserByConditions(HashMap params) {
        return userMapper.select(params);
    }


    public User getUserById(int userId) {
        return userMapper.select(
                new HashMap() {{
                    put("userId", userId);
                }}
        ).get(0);
    }

    public User getUserByName(String userName) {
        List<User> userList = userMapper.select(
                new HashMap() {{
                    put("userNameEqual", userName);
                }}
        );
        if (userList.size() > 0) {
            return userList.get(0);
        } else {
            return null;
        }
    }


    public int addUser(User user) {
        return userMapper.insert(user);
    }

    public int deleteUser(int userId) {
        HashMap<String, Object> params = new HashMap<String, Object>() {{
            put("userId", userId);
        }};
        return userMapper.delete(params);
    }

    public int updateUser(User user) {
        return userMapper.update(user);
    }


}
