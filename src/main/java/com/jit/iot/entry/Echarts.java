package com.jit.iot.entry;

import java.util.List;

/**
 * @packageName: com.jit.iot.entry
 * @className: Echarts
 * @Description:
 * @author: xxz
 * @date: 2019/8/7 10:51
 */

public class Echarts {
    private List<String> legend;//name
    private List<String> axis ;//横坐标
    private List<MyDataSeries> series;//数据项

    public Echarts(List<String> legend, List<String> axis, List<MyDataSeries> series) {
        super();
        this.legend = legend;
        this.axis = axis;
        this.series = series;
    }

    public List<String> getLegend() {
        return legend;
    }

    public void setLegend(List<String> legend) {
        this.legend = legend;
    }

    public List<String> getAxis() {
        return axis;
    }

    public void setAxis(List<String> axis) {
        this.axis = axis;
    }

    public List<MyDataSeries> getSeries() {
        return series;
    }

    public void setSeries(List<MyDataSeries> series) {
        this.series = series;
    }


}
