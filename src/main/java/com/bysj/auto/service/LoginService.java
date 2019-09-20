package com.bysj.auto.service;

import com.bysj.auto.bean.LoginActive;
import com.bysj.auto.dao.LoginMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

@Service
public class LoginService {

    @Autowired
    LoginMapper loginMapper;


    public int loginSuccessRecord(int userId) {
        LoginActive loginActive = new LoginActive() {{
            setFail(0);
            setUserId(userId);
        }};
        return loginMapper.insert(loginActive);
    }

    public int loginFailRecord(int userId) {
        LoginActive loginActive = new LoginActive() {{
            setFail(1);
            setUserId(userId);
        }};
        return loginMapper.insert(loginActive);
    }


    /**
     * 一小时内登陆失败次数
     **/
    public int countFailInAnHours(int userId) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY) - 1);
        HashMap params = new HashMap() {{
            put("loginTime", df.format(calendar.getTime()));
            put("userId", userId);
        }};
        return loginMapper.countFailAtfer(params);
    }


}
