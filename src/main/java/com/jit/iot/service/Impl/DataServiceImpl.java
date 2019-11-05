package com.jit.iot.service.Impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jit.iot.domain.CommonDataDO;
import com.jit.iot.domain.RelayStatusDO;
import com.jit.iot.entry.Echarts;
import com.jit.iot.entry.MyDataSeries;
import com.jit.iot.entry.Pond;
import com.jit.iot.entry.Sensor;
import com.jit.iot.lnfluxdbDao.InfluxdbDao;
import com.jit.iot.mapper.*;
import com.jit.iot.service.DataService;
import com.jit.iot.service.SensorService;
import com.jit.iot.utils.influxdb.TimeChange;
import org.influxdb.dto.QueryResult.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @className: DataService
 * @author: kay
 * @date: 2019/7/22 17:21
 * @packageName: com.jit.iot.service.Impl
 */
@Service
public class DataServiceImpl implements DataService {
    @Resource
    RelayStatusMapper relayStatusDAO;

    @Resource
    InfluxdbDao influxdbDao;

    @Resource
    PondMapper pondMapper;

    @Resource
    SensorMapper sensorMapper;

    public RelayStatusDO selectOneRelay(Integer gwId, Integer addr){
        List<RelayStatusDO> list = relayStatusDAO.selectList(new EntityWrapper<RelayStatusDO>().eq("gw_id", gwId).eq("addr", addr)
                .orderBy("time", false).last("LIMIT 1"));
        if(null == list || list.size() < 1){
            return null;
        }else return list.get(0);
    }

    public void insertRelay(List<RelayStatusDO> relays){
        if(null != relays && relays.size() > 0) {
            relayStatusDAO.insertBatch(relays);
        }
    }

    //插入历史数据data
    public void insertCommonData(List<CommonDataDO> datas){
        if(null != datas && datas.size() > 0) {
            influxdbDao.insertData(datas);
        }
    }

    //根据gw_id找到对应的所有sensor_id
    public List<Sensor> getSensorByGwId(int gw_id){
        return sensorMapper.findSensorByGwId(gw_id);
    }


    //查询历史数据
    public Echarts selectHisData(int pond_id, long start_time, long end_time){

        //第一步，根据pond_id找到gw_id
        Pond pond = pondMapper.getOnePond(pond_id);
        int gw_id = pond.getGw_id();

        //第二步，根据pond_id找到List<addr485> && sensor_name
        List<Sensor> sensorList = sensorMapper.findByPondId(pond_id);

        //第三步，根据gw_id && addr485 && start_time && end_time去 influx db 的data表里查到数据
        List<MyDataSeries> myList = new ArrayList<>();
        List<String> legend = new ArrayList<>();
        List<String> axis = new ArrayList<>();

        for(Sensor s: sensorList) {
            Series series = influxdbDao.findHistory(gw_id, s.getAddr485(), start_time, end_time);
            if(series != null) {
                List<List<Object>> values = series.getValues(); //字段字集合
                System.out.println("values=====" + values);

                for(int i=0; i<values.size(); ++i) {
                    //插入时间
                    if(!axis.contains(values.get(i).get(0)))
                        axis.add((String) values.get(i).get(0));

                    //插入数据
                    String name = s.getSensor_name() + "**" + values.get(i).get(1);
                    int flag = 0;
                    for(MyDataSeries myDataSeries: myList) {
                        if(myDataSeries.getName().equals(name)) {
                            myDataSeries.getData().add((Double) values.get(i).get(2));
                            flag = 1;
                            break;
                        }
                    }
                    //说明没存这个类型的，就创一个
                    if(flag == 0) {
                        legend.add(name);
                        String type = "line";
                        List<Double> data = new ArrayList<>();
                        data.add((Double) values.get(i).get(2));
                        MyDataSeries myDataSeries = new MyDataSeries(name, type, data);
                        myList.add(myDataSeries);
                    }
                }
            }
        }

        Echarts echarts = new Echarts(legend, axis, myList);
        return echarts;
    }

    //用户查询设备log
    public Series equip_log(int pond_id, long start_time, long end_time){
        return influxdbDao.findLog(pond_id, start_time, end_time);
    }

}
