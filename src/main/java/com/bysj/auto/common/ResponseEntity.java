package com.bysj.auto.common;

import java.util.HashMap;

public class ResponseEntity<T> {
    private int code;
    private String message;
    private T data;

    public ResponseEntity() {
        this.setCode(2000);
    }

    public ResponseEntity(T date) {
        this.data = date;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
        if (ERROR_CODE.containsKey(code)) {
            setMessage(ERROR_CODE.get(code));
        }
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    private HashMap<Integer, String> ERROR_CODE = new HashMap<Integer, String>() {
        {
            put(1000, "暂无数据");

            put(2000, "成功");

            put(3000, "错误请求，不恰当请求");

            put(4000, "参数错误");
            put(4001, "登陆失败，账号错误");
            put(4002, "登陆失败，密码错误");
            put(4003, "登陆失败，尝试次数超出限制");

            put(5000, "请求失败!");


            put(9900, "系统错误");
        }
    };
}
