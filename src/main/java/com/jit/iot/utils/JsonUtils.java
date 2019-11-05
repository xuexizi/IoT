package com.jit.iot.utils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.jit.iot.entry.DmaCtl;
import com.jit.iot.entry.RspValue;
import com.jit.iot.entry.SensorType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.*;
import java.util.*;

/**
 * @packageName: com.jit.iot.utils.hardware
 * @className: JsonUtils
 * @Description:
 * @author: xxz
 * @date: 2019/7/26 21:16
 */

public class JsonUtils {
    public static List<SensorType> sensorList;
    public static List<DmaCtl> DmaCtlList;


    public static List<SensorType> Sensor_list(){
        sensorList = new ArrayList<>();
        sensorList.clear();
        StringBuilder all = new StringBuilder();

        try {
            all = readFile("/Sensor_type.json");
        }catch (Exception e){
            System.out.println("readfile error");
        }

        if(all!=null){
            List<HashMap> hashMaps = fromJson2Map(all.toString());
            for(HashMap h:hashMaps) {
                SensorType sensor = new SensorType();
                sensor.setType_name((String) h.get("type_name"));
                sensor.setFcode((int) h.get("fcode"));
                sensor.setReg((int) h.get("reg"));
                sensor.setLen((int) h.get("len"));

                List<Map> mapList = (List<Map>) h.get("rspvalue");
                List<RspValue> rspList = new ArrayList<RspValue>();
                for (Map h2:mapList){
                    RspValue rspValue = new RspValue();
                    rspValue.setStype((String) h2.get("stype"));
                    rspValue.setValue((String) h2.get("value"));
                    rspValue.setUnit((int) h2.get("unit"));
                    rspList.add(rspValue);
                }
                sensor.setRspValues(rspList);

                sensorList.add(sensor);
            }
        }
        return sensorList;
    }


    public static List<DmaCtl> dmaCtl_list(){
        DmaCtlList = new ArrayList<>();
        DmaCtlList.clear();
        StringBuilder all;

        all = readFile("DmaCtl.json");
        if(all!=null){
            List<HashMap> hashMaps = fromJson2Map(all.toString());
            for(HashMap h:hashMaps) {
                DmaCtl dmaCtl= new DmaCtl();

                dmaCtl.setType((String) h.get("type"));
                dmaCtl.setFcode((int) h.get("fcode"));
                dmaCtl.setRoad((int) h.get("road"));
                dmaCtl.setOn((int) h.get("on"));
                dmaCtl.setOff((int) h.get("off"));

                DmaCtlList.add(dmaCtl);
            }
        }
        return DmaCtlList;
    }


    private static StringBuilder readFile(String filename) {
        //读取到静态资源文件
//        Resource resource = new ClassPathResource(filename);
        File file = new File(filename);
        StringBuilder all = new StringBuilder();
        try {
//            file = resource.getFile();
            //使用io读出数据
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
            String str = null;
            while((str = br.readLine()) != null){
                all.append(str);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return all;
    }


    private static List<HashMap> fromJson2Map(String jsonString) {
        List<HashMap> hashMaps = JSONArray.parseArray(jsonString, HashMap.class);

        for(HashMap jsonMap:hashMaps) {
            HashMap<String, Object> resultMap = new HashMap<String, Object>();
            for (Iterator iter = jsonMap.keySet().iterator(); iter.hasNext(); ) {
                String key = (String) iter.next();
                if (jsonMap.get(key) instanceof JSONArray) {
                    JSONArray jsonArray = (JSONArray) jsonMap.get(key);
                    List list = handleJSONArray(jsonArray);
                    resultMap.put(key, list);
                } else {
                    resultMap.put(key, jsonMap.get(key));
                }
            }
        }
        return hashMaps;
    }


    private static List<HashMap<String, Object>> handleJSONArray(JSONArray jsonArray){
        List list = new ArrayList();
        for (Object object : jsonArray) {
            JSONObject jsonObject = (JSONObject) object;
            HashMap map = new HashMap<String, Object>();
            for (Map.Entry entry : jsonObject.entrySet()) {
                if(entry.getValue() instanceof JSONArray){
                    map.put((String)entry.getKey(), handleJSONArray((JSONArray)entry.getValue()));
                }else{
                    map.put((String)entry.getKey(), entry.getValue());
                }
            }
            list.add(map);
        }
        return list;
    }

}
