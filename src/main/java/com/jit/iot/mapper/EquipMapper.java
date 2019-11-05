package com.jit.iot.mapper;

import com.jit.iot.entry.Equipment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EquipMapper {

    //插入一条设备数据
    @Insert("insert into equipment(equip_name, equip_type, addr485, road, pond_id) " +
            "values(#{equip_name}, #{equip_type}, #{addr485}, #{road}, #{pond_id})")
    int insertEquip(String equip_name, String equip_type, int addr485, int road, int pond_id);

    //根据pond_id查找这个塘口的所有设备信息
    @Select("select * from equipment where pond_id = #{pond_id}")
    List<Equipment> findByPondId(int pond_id);

    //根据add485和road找到某一个设备
    @Select("select * from equipment where addr485 = #{addr485} and road = #{road}")
    Equipment findBy485AndRoad(int addr485, int road);

    //修改设备开关状态
    @Update("update equipment set status = #{status} where equip_id = #{equip_id}")
    int updateStatus(int equip_id, int status);

}
