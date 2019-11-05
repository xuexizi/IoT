package com.jit.iot.entry;

import java.util.List;

/**
 * @packageName: com.jit.iot.entry
 * @className: MyDataSeries
 * @Description:
 * @author: xxz
 * @date: 2019/8/7 10:26
 */

public class MyDataSeries {
    private String name;
    private String type;
    private List<Double> data;

    public MyDataSeries(String name, String type, List<Double> data) {
        super();
        this.name = name;
        this.type = type;
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getData() {
        return data;
    }

    public void setData(List<Double> data) {
        this.data = data;
    }


    @Override
    public String toString() {
        return "Series [name=" + name + ", type=" + type + ", data=" + data + "]";
    }

//    private String name;
//    private String type;
//    private List<Double> data;
//    private List<String> time;
//
//    public MyDataSeries(String name, String type, List<Double> data, List<String> time) {
//        super();
//        this.name = name;
//        this.type = type;
//        this.data = data;
//        this.time = time;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public List<Double> getData() {
//        return data;
//    }
//
//    public void setData(List<Double> data) {
//        this.data = data;
//    }
//
//    public List<String> getTime() {
//        return time;
//    }
//
//    public void setTime(List<String> time) {
//        this.time = time;
//    }
//
//    @Override
//    public String toString() {
//        return "Series [name=" + name + ", type=" + type + ", data=" + data + ", time=" + time + "]";
//    }

}
