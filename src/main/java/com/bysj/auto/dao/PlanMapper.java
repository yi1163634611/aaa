package com.bysj.auto.dao;

import com.bysj.auto.bean.Plan;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface PlanMapper {


    @Select("select id , name ,merchants from plan")
    List<Plan> select();


    @Select("select id , name ,merchants from plan where id = #{id}")
    Plan selectById(int id);

    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @Insert("INSERT INTO plan(name, merchants) VALUES (#{name}, #{merchants})")
    int insertPlan(Plan plan);

}
