package com.jit.iot.controller;

import com.jit.iot.entry.Echarts;
import com.jit.iot.entry.MyDataSeries;
import com.jit.iot.service.DataService;
import org.influxdb.dto.QueryResult.*;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @packageName: com.jit.iot.controller
 * @className: DataController
 * @Description:
 * @author: xxz
 * @date: 2019/7/26 9:13
 */

@RestController
@CrossOrigin
public class DataController {
    @Resource
    DataService dataService;

    //查看历史数据
    @RequestMapping(value = "/pond_history_req", method = RequestMethod.POST)
    public Echarts pondHistory(@RequestParam(value = "pond_id") int pond_id,
                               @RequestParam(value = "start_time") long start_time,
                               @RequestParam(value = "end_time") long end_time,
                               HttpServletResponse httpServletResponse) throws Exception{

        System.out.println("查看历史数据=========");

        //调用service
        return dataService.selectHisData(pond_id, start_time, end_time);
    }

    //用户查询设备log
    @RequestMapping(value = "/equip_log_req", method = RequestMethod.POST)
    public Series EquipLog(@RequestParam(value = "pond_id") int pond_id,
                           @RequestParam(value = "start_time") long start_time,
                           @RequestParam(value = "end_time") long end_time,
                           HttpServletResponse httpServletResponse) {

        System.out.println("用户查询设备信息=========");
        System.out.println("pond_id:" + pond_id);

        return dataService.equip_log(pond_id, start_time, end_time);
    }


}
