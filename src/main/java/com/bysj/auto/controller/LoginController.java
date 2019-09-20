package com.bysj.auto.controller;

import com.bysj.auto.bean.User;
import com.bysj.auto.common.ResponseEntity;
import com.bysj.auto.service.LoginService;
import com.bysj.auto.service.UserService;
import com.bysj.auto.util.CookieUtil;
import com.bysj.auto.util.HttpUtil;
import com.bysj.auto.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginService loginService;

    @PostMapping
    @ResponseBody
    public ResponseEntity login(
            @RequestBody User inputUser
    ) {
        ResponseEntity responseEntity = new ResponseEntity();
        //验证账号密码
        User user = userService.getUserByName(inputUser.getUserName());
        if (user == null) {
            responseEntity.setCode(4001);
        } else {
            // 登陆失败次数上线
            //            int failCountLimit = 3;
            //            int userId = user.getUserId();
            //            // 查询一小时内的登陆失败次数
            int failCount = loginService.countFailInAnHours(userId);

            if (failCount >= failCountLimit) {
                responseEntity.setCode(4003);
            } else if (!user.getPassword().equals(inputUser.getPassword())) {
                responseEntity.setCode(4002);
                loginService.loginFailRecord(userId);
                responseEntity.setData(failCountLimit - failCount - 1);
            } else {
                loginService.loginSuccessRecord(userId);
                //返回用户信息 token
                String token = JWTUtil.createToken(user);
                responseEntity.setData(token);
            }
        }

        return responseEntity;
    }


}
