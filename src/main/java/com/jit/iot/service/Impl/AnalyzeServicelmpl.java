package com.jit.iot.service.Impl;

import com.jit.iot.domain.CommonDataDO;
import com.jit.iot.domain.RelayStatusDO;
import com.jit.iot.entry.DmaCtl;
import com.jit.iot.entry.Equipment;
import com.jit.iot.entry.Sensor;
import com.jit.iot.entry.SensorType;
import com.jit.iot.mapper.EquipMapper;
import com.jit.iot.service.AnalyzeService;
import com.jit.iot.service.DataService;
import com.jit.iot.service.EquipService;
import com.jit.iot.utils.JsonUtils;
import com.jit.iot.utils.ObjectToMap;
import com.jit.iot.utils.hardware.*;
import com.jit.iot.utils.redis.RedisCacheManager;
import org.apache.mina.core.session.IoSession;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

import static com.jit.iot.IotApplication.logger;

/**
 * @className: AnalyzeServicelmpl
 * @author: kay
 * @date: 2019/7/22 15:09
 * @packageName: com.jit.iot.service.Impl
 */
@Service
public class AnalyzeServicelmpl implements AnalyzeService {

    private static final String CMD_REGIST = "register";
    private static final String CMD_BYE = "bye";
    private static final String CMD_REPORT = "report";
    private static final String CMD_COMM = "comm";

    @Resource
    private RedisCacheManager redisCacheManager;

    @Resource
    private DataService dataService;

    @Resource
    private EquipService equipService;

    @Resource
    private EquipMapper equipMapper;


    public void AnalyzeData(IoSession session, Object message)throws Exception {
        String str = message.toString();
        String clientAddress = session.getRemoteAddress().toString().replace("/", "");
        logger.info("接受来自 {} 的信息:{}", clientAddress, str);
        RecieveData recieveData;
        try {
            recieveData = JacksonUtils.readValue(str, RecieveData.class);
        } catch (IOException e) {
            logger.error("地址为 {} 的 {} 消息解码错误: ", clientAddress, str, e);
            session.write("{\"terminal\":\"server\",\"msgType\":\"msgERROR\"}\r\n");
            return;
        }
        if (null == recieveData) return;
        String cmd = recieveData.getMsgType();
        switch (cmd) {
            case CMD_REGIST:
                onLine(session, recieveData);
                break;
            case CMD_BYE:
                offLine(session, recieveData);
                break;
            case CMD_REPORT:
                reportData(session, recieveData);
                break;
            case CMD_COMM:
                onComm(session, recieveData);
                break;
            default:
                logger.error("Unkown Msg Type, Ignore this msg!!!");
                break;

        }
    }


    /**
     * 同步redis中的网关数据
     * @param clientAddress  设备地址
     * @param recieveData 接收到的消息
     */
    public void synchronizeRedis(String clientAddress, RecieveData recieveData){
        int gwId = recieveData.getDeviceID();
        String key = "GW" + gwId;
        boolean exists=redisCacheManager.hasKey(key);
        List<ReportData> dataList = recieveData.getContent();
        DeviceOnlineData online;
        if(exists){
            //更新网关缓存
            online = redisCacheManager.getDevice(key);
        }else{
            //新增网关缓存
            online = new DeviceOnlineData();
            online.setId(recieveData.getDeviceID());
            online.setAddress(clientAddress);
        }

        List<RelayStatusDO> relaysdb = new ArrayList<>();

        if(null != dataList){
            for(ReportData bean:dataList) {
                if (bean.getType().contains("relay") || bean.getType().contains("dma")) {

                    if((Double.valueOf(bean.getValue())).intValue() != 0){
                        int addr = bean.getAddr();
                        String value = Integer.toBinaryString((Double.valueOf(bean.getValue())).intValue());
                        //value前补0成8位
                        while(value.length() < 8){
                            value = "0" + value;
                        }
                        for(int i=0; i < 8; ++i){
                            int status = value.charAt(i) - '0';
                            Equipment equip = equipMapper.findBy485AndRoad(addr,value.length()-i);
                            if(equip.getStatus() != status) {
                                equipService.modify_status(addr,value.length()-i, status);
                            }
                        }
                    }

                    //缓存相关
                    RelayStatusDO old_relay = dataService.selectOneRelay(gwId, bean.getAddr());
                    Date now = new Date();
                    short former = old_relay == null ? 0 : old_relay.getValue();
                    short current = bean.getValue().shortValue();
                    short mask = (short) ((former & 0xff) ^ (current & 0xff));   //由于Jave中byte是signed, 按位与0xff的目的是只取最低字节
                    short chng_pos = (short) ((current & 0xff) & (mask & 0xff)); //取值范围0~255
                    byte changed = 0, pos = 0, onoff = 0;
                    while (mask > 0) {
                        changed = (byte) (mask % 2);
                        onoff = (byte) ((current & 0xff) >> pos);
                        if (changed == 1) {
                            relaysdb.add(new RelayStatusDO(gwId, bean.getAddr(), (byte)(pos+1), onoff, current, now));
                        }
                        mask = (short) (mask >> 1);
                        pos++;
                    }
                }
            }
        }
        redisCacheManager.setDevice(key, online, 350);
    }


    /**
     * 注册上线
     *
     * @param session   会话信息
     * @param recieveData 消息对象
     */
    void onLine(IoSession session, RecieveData recieveData) {
        if("device".equalsIgnoreCase(recieveData.getTerminal())) {
            String key = "GW" + recieveData.getDeviceID();
            String address = session.getRemoteAddress().toString().replace("/", "");
            redisCacheManager.set(address, key);
            DeviceOnlineData online = new DeviceOnlineData();
            online.setId(recieveData.getDeviceID());
            online.setAddress(address);

            //返回传感器信息
            List<Map> sensors = new ArrayList<>();
            List<Map> relays = new ArrayList<>();
            RegisterReplyData replyBean = new RegisterReplyData("server", recieveData.getDeviceID(),
                    "register", sensors, relays);

            //根据gw_id找到对应的所有sensor
            List<Sensor> sensorList = dataService.getSensorByGwId(recieveData.getDeviceID());

            if ( sensorList != null && sensorList.size() > 0) {

                //得到所有的sensorList的addr485
                Map<String, List> sensorAddrs = new HashMap<>();
                for (Sensor sensor : sensorList) {
                    List<Integer> addr;
                    String sensorType = sensor.getSensor_type();
                    //查看是否已经存在该类型的addr
                    addr = sensorAddrs.get(sensorType);
                    if (null == addr) {
                        addr = new ArrayList<>();
                        addr.add(sensor.getAddr485());
                        sensorAddrs.put(sensorType, addr);
                    } else {
                        addr.add(sensor.getAddr485());
                    }
                }

                for (String mapkey : sensorAddrs.keySet()) {
                    //根据mapkey找到json配置文件里对应的SensorType
                    SensorType sType = new SensorType();
                    for(SensorType st: JsonUtils.sensorList){
                        if(st.getType_name().equals(mapkey))
                            sType = st;
                    }

                    //将sensorTypes转换成map
                    Map map = ObjectToMap.SensorToMap(sType);
                    map.put("addr", sensorAddrs.get(mapkey));
                    sensors.add(map);

                    //生成继电器控制命令传给gw
                    if (mapkey.contains("DMA_STA")) {
                        for(DmaCtl dmactl: JsonUtils.dmaCtl_list()){
                            if(dmactl.getType().equals("ctl_dma")){
                                Map<String, Object> ctlmap = ObjectToMap.CtlToMap(dmactl);
                                ctlmap.put("addr", sensorAddrs.get(mapkey));
                                relays.add(ctlmap);
                                break;
                            }
                        }
                    }

                }
            }

            logger.info("发送注册响应:{}", JacksonUtils.toJson(replyBean));
            session.write(JacksonUtils.toJson(replyBean) + "\r\n");

            //session.write("abctest\t\n");
            //加入缓存
            redisCacheManager.setDevice(key, online, 350);
            logger.info("ID为 {} 的网关上线了!", recieveData.getDeviceID());
        }
    }

    /**
     * 下线
     *
     * @param session     连接信息
     * @param recieveData 消息对象
     */
    void offLine(IoSession session, RecieveData recieveData) {
        String key = "GW" + recieveData.getDeviceID();
        boolean exists=redisCacheManager.hasKey(key);
        if(exists){
            redisCacheManager.del(key);
        }
    }

    /**
     * server端的查询/控制透传功能，将query与control消息的请求与响应在APP与网关之间透传
     *
     * @param session     会话
     * @param recieveData 消息对象
     */
    void onComm(IoSession session, RecieveData recieveData) {
        String clientAddress = session.getRemoteAddress().toString().replace("/", "");
        String key = "GW" + recieveData.getDeviceID();
        if(recieveData.getTerminal().equalsIgnoreCase("device")){
            //网关响应，透传给APP
            String appAddress = recieveData.getTo();
            CommonReplyData reply = new CommonReplyData(recieveData.getTerminal(),recieveData.getMsgType(),
                    recieveData.getDeviceID(),recieveData.getContent(),"success");
            for (IoSession value : session.getService().getManagedSessions().values()) {
                if (value.getRemoteAddress().toString().replace("/", "").equalsIgnoreCase(appAddress)) {
                    value.write(JacksonUtils.toJson(reply) + "\r\n");
                    logger.info("收到GW{}响应透传给APP{},消息内容:{}", clientAddress, appAddress, JacksonUtils.toJson(reply));
                    break;
                }
            }

            //同步，同步设备状态
            synchronizeRedis(clientAddress, recieveData);

        }else{
            //APP请求，透传给网关
            boolean exists = redisCacheManager.hasKey(key);
            if (!exists) {
                //网关不在线
                recieveData.setTerminal("server");
                session.write(JacksonUtils.toJson(recieveData) + "\r\n");
                logger.info("请求网关不在线:{}", key);
            } else {
                //透传给网关
                String gwAddress;
                DeviceOnlineData online = redisCacheManager.getDevice(key);
                gwAddress = online.getAddress();
                recieveData.setTo(session.getRemoteAddress().toString().replace("/", ""));//设置To

                String control =recieveData.getOrder();
                System.out.println(control);
                for (IoSession value : session.getService().getManagedSessions().values()) {
                    if ((value.getRemoteAddress().toString().replace("/", "")).equalsIgnoreCase(gwAddress)) {
                        value.write(JacksonUtils.toJson(recieveData) + "\r\n");
                        logger.info("向GW{}透传消息:{}",gwAddress, JacksonUtils.toJson(recieveData ));
                        break;
                    }
                }

                if(!control.equals("query")){
                    String[] status = control.split("#");
                    equipService.modify_status(Integer.parseInt(status[1]), Integer.parseInt(status[2]), Integer.parseInt(status[3]));

                }
            }
        }
    }


    /**
     * 周期上报
     *
     * @param session     会话
     * @param recieveData 消息对象
     * @throws ParseException
     */
    void reportData(IoSession session, RecieveData recieveData) throws ParseException {
        String clientAddress = session.getRemoteAddress().toString().replace("/", "");
        //同步
        synchronizeRedis(clientAddress,recieveData);
        //入库
        int gwId = recieveData.getDeviceID();
        List<ReportData> datas = recieveData.getContent();
        if(null != datas){
            Date now = new Date();
            List<CommonDataDO> commons = new ArrayList<>();
            for(ReportData data:datas){
                if(data.getType().contains("dma")){
                    continue;//同步中已将继电器数据插入数据库
                }else{
                    commons.add(new CommonDataDO(gwId, data.getAddr(), data.getReg(), data.getType(), data.getValue(), now));
                }
            }
            //插入数据库
            dataService.insertCommonData(commons);
        }
    }

}
