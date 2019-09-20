package com.bysj.auto.dao;

import com.bysj.auto.bean.LoginActive;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;

@Mapper
public interface LoginMapper {

    @Insert("insert into login(userId,fail) values(#{userId},#{fail})")
    int insert(LoginActive loginActive);

    @Select("select count(0) from login where fail=1 and loginTime > #{loginTime} and userId = #{userId}")
    int countFailAtfer(HashMap params);

}
