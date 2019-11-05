package com.jit.iot.mapper;

import com.jit.iot.entry.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @className: UserMapper
 * @author: kay
 * @date: 2019/7/19 16:37
 * @packageName: com.jit.iot.mapper
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where phone = #{phone}")
    User findByTel(@Param("phone") String phone);

    @Insert("insert into user(username, password, phone) values(#{username}, #{password}, #{phone})")
    int insertUser(String username, String password, String phone);


}
