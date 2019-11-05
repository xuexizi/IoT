package com.jit.iot.controller;

import com.jit.iot.entry.Pond;
import com.jit.iot.service.PondService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @packageName: com.jit.iot.controller
 * @className: PondService
 * @Description:
 * @author: xxz
 * @date: 2019/7/25 10:05
 */

@RestController
@CrossOrigin
public class PondController {

    @Resource
    PondService pondService;

    //用户新增塘口
    @RequestMapping(value = "/pond_add_req", method = RequestMethod.POST)
    public String PondAdd(@RequestParam(value = "user_id") int user_id,
                           @RequestParam(value = "length") double length,
                           @RequestParam(value = "width") double width,
                           @RequestParam(value = "longitude") double longitude,
                           @RequestParam(value = "latitude") double latitude,
                           @RequestParam(value = "type") String type,
                           @RequestParam(value = "pond_name") String pond_name,
                           @RequestParam(value = "gw_id") int gw_id,
                           HttpServletResponse httpServletResponse) throws Exception{

        System.out.println("用户新增塘口=========");
        System.out.println("user_id:" + user_id);
        System.out.println("length:" + length);
        System.out.println("width:" + width);
        System.out.println("longitude:" + longitude);
        System.out.println("latitude:" + latitude);
        System.out.println("type:" + type);
        System.out.println("pond_name:" + pond_name);
        System.out.println("gw_id:" + gw_id);


        //调用service
        return pondService.add_pond(user_id, length, width, longitude, latitude, type, pond_name, gw_id);
    }

    //用户查询塘口信息
    @RequestMapping(value = "/pond_list_req", method = RequestMethod.POST)
    public List<Pond> PondList(@RequestParam(value = "user_id") int user_id,
                               HttpServletResponse httpServletResponse) {

        System.out.println("用户查询塘口信息=========");
        System.out.println("user_id:" + user_id);

        return pondService.pond_list(user_id);
    }

}
