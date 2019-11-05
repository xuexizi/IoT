package com.jit.iot.controller;

import com.jit.iot.entry.Sensor;
import com.jit.iot.entry.SensorType;
import com.jit.iot.service.SensorService;
import com.jit.iot.utils.JsonUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @packageName: com.jit.iot.controller
 * @className: SensorCroller
 * @Description:
 * @author: xxz
 * @date: 2019/7/25 10:05
 */

@RestController
@CrossOrigin
public class SensorController {

    @Resource
    SensorService sensorService;


    //刷新传感器类型
    @RequestMapping(value = "/sensor_refresh", method = RequestMethod.POST)
    public void sensor_refresh(HttpServletResponse httpServletResponse) {

        System.out.println("刷新传感器类型=========");
        JsonUtils.Sensor_list();

    }

    //用户查看可以添加的传感器类型
    @RequestMapping(value = "/sensor_show_req", method = RequestMethod.POST)
    public List<String> sensor_show_list(HttpServletResponse httpServletResponse) {

        System.out.println("用户查看可以添加的传感器类型=========");

        return sensorService.pond_show_list();
    }

    //用户新增传感器
    @RequestMapping(value = "/sensor_add_req", method = RequestMethod.POST)
    public String SensorAdd(@RequestParam(value = "pond_id") int pond_id,
                          @RequestParam(value = "sensor_type") String sensor_type,
                          @RequestParam(value = "addr485") int addr485,
                          @RequestParam(value = "sensor_name") String sensor_name,
                          HttpServletResponse httpServletResponse) throws Exception{

        System.out.println("用户新增传感器=========");
        System.out.println("pond_id:" + pond_id);
        System.out.println("sensor_type:" + sensor_type);
        System.out.println("addr485:" + addr485);
        System.out.println("sensor_name:" + sensor_name);

        //调用service
        return sensorService.sensor_add(pond_id, sensor_type, addr485, sensor_name);
    }

    //用户查询传感器信息
    @RequestMapping(value = "/sensor_list_req", method = RequestMethod.POST)
    public List<Sensor> SensorList(@RequestParam(value = "pond_id") int pond_id,
                                   HttpServletResponse httpServletResponse) {

        System.out.println("用户查询传感器信息=========");
        System.out.println("pond_id:" + pond_id);

        return sensorService.pond_list(pond_id);
    }

}
