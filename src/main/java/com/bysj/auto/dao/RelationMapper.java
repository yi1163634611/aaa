package com.bysj.auto.dao;

import com.bysj.auto.bean.Relation;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RelationMapper {

    @Select("select id,productId,planId from relation where planId = #{planId}")
    List<Relation> select(int planId);

    @Insert("insert into relation(productId,planId,num) values(#{productId},#{planId},#{num})")
    int insert(Relation relation);

}
