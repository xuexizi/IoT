package com.jit.iot.service;

import com.jit.iot.domain.CommonDataDO;
import com.jit.iot.domain.RelayStatusDO;
import com.jit.iot.entry.Echarts;
import com.jit.iot.entry.MyDataSeries;
import com.jit.iot.entry.Sensor;
import org.influxdb.dto.QueryResult.*;

import java.util.List;

public interface DataService {

    public RelayStatusDO selectOneRelay(Integer gwId, Integer addr);

    public void insertRelay(List<RelayStatusDO> relays);

    //插入历史数据data
    public void insertCommonData(List<CommonDataDO> datas);

    //根据gw_id得到该网管下所有传感器
    public List<Sensor> getSensorByGwId(int gw_id);

    //查询历史数据
    public Echarts selectHisData(int pond_id, long start_time, long end_time);

    //用户查询设备log
    public Series equip_log(int pond_id, long start_time, long end_time);

}
