package com.bysj.auto.dao;

import com.bysj.auto.bean.User;
import com.bysj.auto.provider.UserProvider;
import org.apache.ibatis.annotations.*;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface UserMapper {

    @SelectProvider(type = UserProvider.class, method = "select")
    List<User> select(HashMap params);

    @Options(useGeneratedKeys = true, keyColumn = "userId")
    @InsertProvider(type = UserProvider.class, method = "insert")
    int insert(User user);

    @DeleteProvider(type = UserProvider.class, method = "delete")
    int delete(HashMap params);

    @UpdateProvider(type = UserProvider.class, method = "update")
    int update(User user);

}
