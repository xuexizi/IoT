package com.jit.iot.entry;

/**
 * @packageName: com.jit.iot.entry
 * @className: Equipment
 * @Description:
 * @author: xxz
 * @date: 2019/7/30 19:01
 */

public class Equipment {
    private int equip_id;
    private String equip_name;
    private String equip_type;
    private int addr485;
    private int road;
    private int status;
    private int pond_id;

    public int getEquip_id() {
        return equip_id;
    }

    public void setEquip_id(int equip_id) {
        this.equip_id = equip_id;
    }

    public String getEquip_name() {
        return equip_name;
    }

    public void setEquip_name(String equip_name) {
        this.equip_name = equip_name;
    }

    public String getEquip_type() {
        return equip_type;
    }

    public void setEquip_type(String equip_type) {
        this.equip_type = equip_type;
    }

    public int getAddr485() {
        return addr485;
    }

    public void setAddr485(int addr485) {
        this.addr485 = addr485;
    }

    public int getRoad() {
        return road;
    }

    public void setRoad(int road) {
        this.road = road;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getPond_id() {
        return pond_id;
    }

    public void setPond_id(int pond_id) {
        this.pond_id = pond_id;
    }
}
