package com.jit.iot.config;

import com.jit.iot.entry.DmaCtl;
import com.jit.iot.entry.SensorType;
import com.jit.iot.utils.JsonUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.*;

/**
 * @packageName: com.jit.iot.config
 * @className: SensorTypeConfig
 * @Description:
 * @author: xxz
 * @date: 2019/7/26 17:19
 */

@Configuration
public class SensorTypeConfig {
    @Bean
    public List<SensorType> Sensors_list(){
        return JsonUtils.Sensor_list();
    }

    @Bean
    public List<DmaCtl> DmaCtl_list(){
        return JsonUtils.dmaCtl_list();
    }

}
