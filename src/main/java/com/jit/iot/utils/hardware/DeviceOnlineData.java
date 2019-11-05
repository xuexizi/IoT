package com.jit.iot.utils.hardware;

import java.util.List;

/**
 * @className: DeviceOnlineData
 * @author: kay
 * @date: 2019/7/22 15:25
 * @packageName: com.jit.iot.utils.hardware
 */
public class DeviceOnlineData {
    private Integer id; // 网关ID
    private List<ReportData> sensors; //在线传感器列
    private List<RelayData> relays; //在线继电器各路状态
    private String address; //网关IP

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<ReportData> getSensors() {
        return sensors;
    }

    public void setSensors(List<ReportData> sensors) {
        this.sensors = sensors;
    }

    public List<RelayData> getRelays() {
        return relays;
    }

    public void setRelays(List<RelayData> relays) {
        this.relays = relays;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "DeviceOnlineData{" +
                "deviceID=" + id +
                ", sensors=" + sensors +
                ", relays=" + relays +
                ", address='" + address + '\'' +
                '}';
    }
}
