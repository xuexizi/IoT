package com.jit.iot.entry;

/**
 * @packageName: com.jit.iot.entry
 * @className: Sensor
 * @Description:
 * @author: xxz
 * @date: 2019/7/25 8:56
 */

public class Sensor {
    private int sensor_id;
    private String sensor_type;
    private int addr485;
    private String sensor_name;
    private int pond_id;

    public int getSensor_id() {
        return sensor_id;
    }

    public void setSensor_id(int sensor_id) {
        this.sensor_id = sensor_id;
    }

    public String getSensor_type() {
        return sensor_type;
    }

    public void setSensor_type(String sensor_type) {
        this.sensor_type = sensor_type;
    }

    public int getAddr485() {
        return addr485;
    }

    public void setAddr485(int addr485) {
        this.addr485 = addr485;
    }

    public String getSensor_name() {
        return sensor_name;
    }

    public void setSensor_name(String sensor_name) {
        this.sensor_name = sensor_name;
    }

    public int getPond_id() {
        return pond_id;
    }

    public void setPond_id(int pond_id) {
        this.pond_id = pond_id;
    }
}
