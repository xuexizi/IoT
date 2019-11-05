package com.jit.iot.entry;

import java.util.List;

/**
 * @packageName: com.jit.iot.entry
 * @className: SensorType
 * @Description:
 * @author: xxz
 * @date: 2019/7/26 17:02
 */

public class SensorType {
    public String type_name;
    public int fcode;
    public int reg;
    public int len;
    public List<RspValue> rspValues;

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public int getFcode() {
        return fcode;
    }

    public void setFcode(int fcode) {
        this.fcode = fcode;
    }

    public int getReg() {
        return reg;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public List<RspValue> getRspValues() {
        return rspValues;
    }

    public void setRspValues(List<RspValue> rspValues) {
        this.rspValues = rspValues;
    }
}
