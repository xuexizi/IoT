package com.jit.iot.mapper;

import com.jit.iot.entry.Pond;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PondMapper {

    //插入一条塘口数据
    @Insert("insert into pond(length, width, longitude, latitude, type, pond_name, user_id, gw_id) " +
            "values(#{length}, #{width}, #{longitude}, #{latitude}, #{type}, #{pond_name}, #{user_id}, #{gw_id})")
    int insertPond(double length, double width, double longitude, double latitude,
                   String type, String pond_name, int user_id, int gw_id);

    //查询塘口信息
    @Select("select * from pond where user_id = #{user_id}")
    List<Pond> getPondsInfo(int user_id);

    //根据pond_id得到这个塘口的数据
    @Select("select * from pond where pond_id = #{pond_id}")
    Pond getOnePond(int pond_id);

}
