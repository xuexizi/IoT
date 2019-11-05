package com.jit.iot.entry;

/**
 * @packageName: com.jit.iot.entry
 * @className: Pond
 * @Description:
 * @author: xxz
 * @date: 2019/7/25 8:59
 */

public class Pond {
    private int pond_id;
    private double length;
    private double width;
    private double longitude;
    private double latitude;
    private String type;
    private String pond_name;
    private int user_id;
    private int gw_id;

    public int getPond_id() {
        return pond_id;
    }

    public void setPond_id(int pond_id) {
        this.pond_id = pond_id;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPond_name() {
        return pond_name;
    }

    public void setPond_name(String pond_name) {
        this.pond_name = pond_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getGw_id() {
        return gw_id;
    }

    public void setGw_id(int gw_id) {
        this.gw_id = gw_id;
    }
}
