package com.jit.iot.service.Impl;

import com.jit.iot.entry.Equipment;
import com.jit.iot.lnfluxdbDao.InfluxdbDao;
import com.jit.iot.mapper.EquipMapper;
import com.jit.iot.service.EquipService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @packageName: com.jit.iot.service.Impl
 * @className: EquipServiceImpl
 * @Description:
 * @author: xxz
 * @date: 2019/7/30 19:27
 */

@Service
public class EquipServiceImpl implements EquipService {

    @Resource
    EquipMapper equipMapper;

    @Resource
    InfluxdbDao influxdbDao;

    //用户查看可以添加的设备类型
    public List<String> equip_show_list(){
        List<String> list = new ArrayList<>();
        list.add("水车");
        list.add("电灯");
        list.add("风扇");
        return list;
    }

    //用户新增设备
    public String equip_add(int pond_id, String equip_type, String equip_name, int addr485, int road){
        int ret = equipMapper.insertEquip(equip_name, equip_type, addr485, road, pond_id);
        if(ret < 0){
            return "fail";
        } else {
            return "success";
        }
    }

    //用户查询某塘口下的设备信息
    public List<Equipment> equip_list(int pond_id){
        return equipMapper.findByPondId(pond_id);
    }

    //用户修改设备开关
    public void modify_status(int add485, int road, int status) {
        Equipment equipment = equipMapper.findBy485AndRoad(add485, road);

        if (equipment != null) {
            int pond_id = equipment.getPond_id();
            String equip_name = equipment.getEquip_name();
            int equip_id = equipment.getEquip_id();

            influxdbDao.insertOneLog(pond_id, equip_name, status);
            equipMapper.updateStatus(equip_id, status);
        }
    }

}
