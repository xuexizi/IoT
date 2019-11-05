package com.jit.iot.entry;

/**
 * @packageName: com.jit.iot.entry
 * @className: RspValue
 * @Description:
 * @author: xxz
 * @date: 2019/7/26 17:09
 */

public class RspValue {
    public String stype;
    public String value;
    public int unit;

    public String getStype() {
        return stype;
    }

    public void setStype(String stype) {
        this.stype = stype;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }
}
