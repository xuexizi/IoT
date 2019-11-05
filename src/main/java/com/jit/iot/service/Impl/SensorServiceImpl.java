package com.jit.iot.service.Impl;

import com.jit.iot.entry.Sensor;
import com.jit.iot.entry.SensorType;
import com.jit.iot.mapper.SensorMapper;
import com.jit.iot.service.SensorService;
import com.jit.iot.utils.JsonUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @packageName: com.jit.iot.service.Impl
 * @className: SensorServiceImpl
 * @Description:
 * @author: xxz
 * @date: 2019/7/25 10:13
 */

@Service
public class SensorServiceImpl implements SensorService {

    @Resource
    SensorMapper sensorMapper;

    //用户查看可以添加的传感器类型
    public List<String> pond_show_list() {
        List<String> list = new ArrayList<>();

        for(SensorType s:JsonUtils.sensorList) {
            list.add(s.getType_name());
        }
        return list;
    }

    //用户新增传感器
    public String sensor_add(int pond_id, String sensor_type, int addr485, String sensor_name){
        int ret = sensorMapper.insertSensor(sensor_type, addr485, sensor_name, pond_id);
        if(ret < 0){
            return "fail";
        } else {
            return "success";
        }
    }

    //用户查询某塘口下的传感器信息
    public List<Sensor> pond_list(int pond_id){
        return sensorMapper.findByPondId(pond_id);
    }

}
