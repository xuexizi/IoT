package com.jit.iot.lnfluxdbDao.Impl;

import com.jit.iot.config.InfluxDbConfig;
import com.jit.iot.domain.CommonDataDO;
import com.jit.iot.lnfluxdbDao.InfluxdbDao;
import com.jit.iot.utils.influxdb.InfluxDBConnect;
import com.jit.iot.utils.influxdb.TimeChange;
import org.influxdb.dto.QueryResult;
import org.influxdb.dto.QueryResult.Series;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;


@Repository
public class InfluxdbDaoImpl  implements InfluxdbDao {

    @Resource
    private InfluxDbConfig influxdbConfig;

    //插入data数据
    public void insertData(List<CommonDataDO > commons){
        InfluxDBConnect influx_conn = influxdbConfig.influxDbUtils();
        String measurement = "history_data";

        //获取当前系统时间
        long time = new Date().getTime();
        for(int i=0; i<commons.size(); ++i) {
            Map<String, String> tags = new HashMap<>();
            Map<String, Object> fields = new HashMap<>();

            tags.put("gw_id", String.valueOf(commons.get(i).getGw_id()));
            tags.put("addr485", String.valueOf(commons.get(i).getAddr()));
            tags.put("type", commons.get(i).getType());
            fields.put("value", commons.get(i).getValue());

            influx_conn.insert(measurement, tags, fields, time, TimeUnit.MILLISECONDS);
        }
    }

    //插入一条log数据
    public void insertOneLog(int pond_id, String equip_name, int status) {
        InfluxDBConnect influx_conn = influxdbConfig.influxDbUtils();
        String measurement = "log";

        Map<String, String> tags = new HashMap<String, String>();
        Map<String, Object> fields = new HashMap<String, Object>();

        tags.put("pond_id", String.valueOf(pond_id));
        fields.put("equip_name", equip_name);
        if(status == 0)
            fields.put("action", "关掉");
        else
            fields.put("action", "打开");

        influx_conn.insert(measurement, tags, fields, System.currentTimeMillis(), TimeUnit.MILLISECONDS);

    }


    //查询data数据
    public Series findHistory(int gw_id, int addr485, long start_time, long end_time) {
        //数据格式转换
        String start = start_time + "000000";
        String end = end_time + "000000";
        String command = "select type,value from history_data where time>=" + start + " and time<=" + end + " and gw_id='" +gw_id+"'" + " and addr485='" + addr485+"'";
        return findInflux(command);
    }

    //查询log的数据
    public Series findLog(int pond_id, long start_time, long end_time){
        //数据格式转换
        String start = start_time + "000000";
        String end = end_time + "000000";
        String command = "select equip_name,action from log where time>=" + start + " and time<=" + end + " and pond_id='" +pond_id+"'";
        return findInflux(command);
    }


    //查询
    public Series findInflux(String command) {
        InfluxDBConnect influx_conn = influxdbConfig.influxDbUtils();
        System.out.println("查询influx db：command=" + command);
        QueryResult results = influx_conn.query(command);

        if (results.getResults().get(0).getSeries() == null) {
            return null;
        }

        Series series = results.getResults().get(0).getSeries().get(0);
        return TimeChange.changeSeriesTime(series);
    }


}
