package com.jit.iot.entry;

/**
 * @packageName: com.jit.iot.entry
 * @className: DmaCtl
 * @Description:
 * @author: xxz
 * @date: 2019/7/29 15:46
 */

public class DmaCtl {
    public String type;
    public int fcode;
    public int road;
    public int on;
    public int off;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFcode() {
        return fcode;
    }

    public void setFcode(int fcode) {
        this.fcode = fcode;
    }

    public int getRoad() {
        return road;
    }

    public void setRoad(int road) {
        this.road = road;
    }

    public int getOn() {
        return on;
    }

    public void setOn(int on) {
        this.on = on;
    }

    public int getOff() {
        return off;
    }

    public void setOff(int off) {
        this.off = off;
    }
}
