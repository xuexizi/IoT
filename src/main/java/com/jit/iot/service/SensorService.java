package com.jit.iot.service;

import com.jit.iot.entry.Sensor;
import java.util.List;

public interface SensorService {

    //用户查看可以添加的传感器类型
    public List<String> pond_show_list();

    //用户新增传感器
    public String sensor_add(int pond_id, String sensor_type, int addr485, String sensor_name);

    //用户查询传感器信息
    public List<Sensor> pond_list(int pond_id);
}
