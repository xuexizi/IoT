package com.jit.iot.lnfluxdbDao;

import com.jit.iot.domain.CommonDataDO;
import org.influxdb.dto.QueryResult.Series;
import java.util.List;

/**
 * @className: InfluxdbDao
 * @author: xxz
 * @date: 2019/7/19 11:04
 * @packageName: com.jit.iot.lnfluxdbDao
 */
public interface InfluxdbDao {

    //插入data数据
    public void insertData(List<CommonDataDO> commons);

    //插入一条log数据
    public void insertOneLog(int pond_id, String equip_name, int status);

    //根据时间读取data的数据
    Series findHistory(int gw_id, int addr485, long start_time, long end_time);

    //根据时间读取log的数据
    Series findLog(int pond_id, long start_time, long end_time);

}
