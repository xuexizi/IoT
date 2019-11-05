package com.jit.iot.utils;

import com.jit.iot.entry.DmaCtl;
import com.jit.iot.entry.RspValue;
import com.jit.iot.entry.SensorType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: ObjectToMap
 * @author: kay
 * @date: 2019/7/29 15:04
 * @packageName: com.jit.iot.utils.json
 */
public class ObjectToMap {

    public static Map SensorToMap(SensorType sensorType) {
            Map map = new HashMap<>();

            map.put("len", sensorType.getLen());
            map.put("reg", sensorType.getReg());
            map.put("type", sensorType.getType_name());
            map.put("fcode", sensorType.getFcode());

            List<Map> rspMaps = new ArrayList<>();
            for (RspValue repValue : sensorType.getRspValues()) {
                Map rspMap = new HashMap<>();
                rspMap.put("stype", repValue.getStype());

                String[] strings = repValue.getValue().split("#");
                int[] ints = new int[strings.length];
                for (int i = 0; i < strings.length; i++) {
                    ints[i] = Integer.parseInt(strings[i]);
                }
                rspMap.put("value", ints);
                rspMap.put("unit", repValue.getUnit());
                rspMaps.add(rspMap);
            }
            map.put("rspvalue", rspMaps);

        return map;
    }

    public static Map CtlToMap(DmaCtl dmaCtl) {
        Map map = new HashMap<>();

        map.put("type", dmaCtl.getType());
        map.put("fcode", dmaCtl.getFcode());
        map.put("road", dmaCtl.getRoad());
        map.put("on", dmaCtl.getOn());
        map.put("off", dmaCtl.getOff());

        return map;
    }

}
