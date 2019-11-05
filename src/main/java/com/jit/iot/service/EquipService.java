package com.jit.iot.service;

import com.jit.iot.entry.Equipment;
import java.util.List;

public interface EquipService {
    //用户查看可以添加的设备类型
    public List<String> equip_show_list();

    //用户新增设备
    public String equip_add(int pond_id, String equip_type, String equip_name, int addr485, int road);

    //用户查询设备信息
    public List<Equipment> equip_list(int pond_id);

    //用户修改设备开关
    public void modify_status(int add485, int road, int status);

}
