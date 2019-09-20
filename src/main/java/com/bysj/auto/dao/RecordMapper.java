package com.bysj.auto.dao;

import com.bysj.auto.bean.Record;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RecordMapper {

    @Insert("INSERT INTO `auto`.`record`(`userId`, `productId`, `num`) VALUES (#{userId}, #{productId}, #{num})")
    int insert(Record record);

    @Select("select re.id,re.userId,re.productId,re.num,re.createTime,pr.name as productName " +
            "from record re " +
            "join product pr on pr.id = re.productId " +
            "where userId = #{userId}")
    List<Record> select(int userId);

}
