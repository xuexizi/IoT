package com.jit.iot.utils.hardware;

/**
 * @className: RelayData
 * @author: kay
 * @date: 2019/7/22 15:25
 * @packageName: com.jit.iot.utils.hardware
 */
public class RelayData {
    private Integer addr485;//485地址
    private Integer position; //第几路
    private char status; //状态，0：关闭，1：打开

    public Integer getAddr485() {
        return addr485;
    }

    public void setAddr485(Integer addr485) {
        this.addr485 = addr485;
    }

    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    public char getStatus() {
        return status;
    }

    public void setStatus(char status) {
        this.status = status;
    }

    public RelayData() {
    }

    public RelayData(Integer addr485, Integer position, char status) {
        this.addr485 = addr485;
        this.position = position;
        this.status = status;
    }

    @Override
    public String toString() {
        return "RelayData{" +
                "addr485=" + addr485 +
                ", position=" + position +
                ", status=" + status +
                '}';
    }
}
