package com.jit.iot.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jit.iot.entry.Sensor;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SensorMapper extends BaseMapper<Sensor> {

    //插入一条传感器数据
    @Insert("insert into sensor(sensor_type, addr485, sensor_name, pond_id) " +
            "values(#{sensor_type}, #{addr485}, #{sensor_name}, #{pond_id})")
    int insertSensor(String sensor_type, int addr485, String sensor_name, int pond_id);

    //根据pond_id查找这个塘口的所有传感器信息
    @Select("select * from sensor where pond_id = #{pond_id}")
    List<Sensor> findByPondId(int pond_id);

    //根据gw_id查找该网关下所有塘口的所有传感器信息
    @Select("select sensor_type,addr485 from sensor,pond where gw_id = #{gw_id} and pond.pond_id=sensor.pond_id")
    List<Sensor> findSensorByGwId(int gw_id);

}
