package com.jit.iot.controller;

import com.jit.iot.entry.Equipment;
import com.jit.iot.service.EquipService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @packageName: com.jit.iot.controller
 * @className: EquipController
 * @Description:
 * @author: xxz
 * @date: 2019/7/30 19:27
 */

@RestController
@CrossOrigin
public class EquipController {

    @Resource
    EquipService equipService;

    //用户查看可以添加的设备类型
    @RequestMapping(value = "/equip_show_req", method = RequestMethod.POST)
    public List<String> EquipShowList(HttpServletResponse httpServletResponse) {

        System.out.println("用户查看可以添加的设备类型=========");

        return equipService.equip_show_list();
    }

    //用户新增设备
    @RequestMapping(value = "/equip_add_req", method = RequestMethod.POST)
    public String EquipAdd(@RequestParam(value = "pond_id") int pond_id,
                           @RequestParam(value = "equip_type") String equip_type,
                           @RequestParam(value = "equip_name") String equip_name,
                           @RequestParam(value = "addr485") int addr485,
                           @RequestParam(value = "road") int road,
                           HttpServletResponse httpServletResponse) throws Exception{

        System.out.println("用户新增设备=========");
        System.out.println("pond_id:" + pond_id);
        System.out.println("equip_type:" + equip_type);
        System.out.println("equip_name:" + equip_name);
        System.out.println("addr485:" + addr485);
        System.out.println("road:" + road);

        return equipService.equip_add(pond_id, equip_type, equip_name, addr485, road);
    }

    //用户查询设备信息
    @RequestMapping(value = "/equip_list_req", method = RequestMethod.POST)
    public List<Equipment> EquipList(@RequestParam(value = "pond_id") int pond_id,
                                     HttpServletResponse httpServletResponse) {

        System.out.println("用户查询设备信息=========");
        System.out.println("pond_id:" + pond_id);

        return equipService.equip_list(pond_id);
    }


}
