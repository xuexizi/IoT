package com.jit.iot.utils.enums;

import java.util.*;

/**
 * @className: SensorOrderEnum
 * @author: kay
 * @date: 2019/7/22 15:55
 * @packageName: com.jit.iot.utils.enums
 */
public enum  SensorOrderEnum {
    /**
     * 液位传感器读取指令
     */
    LIQUID_LEVEL("liquidLevel",new HashMap<String, Object>(){
        {
            put("type","liquidLevel");
            put("fcode", 3);
            put("reg",  new int[]{4});
            put("len", 1);
            put("rspvalue",RspValues.LIQUID_POS);
        }
    }),

    SOIL_COMBO("comboSoilHK",new HashMap<String, Object>(){
        {
            put("type","soil_hk");
            put("fcode", 3);
            put("reg",  new int[]{0});
            put("len", 4);
            put("rspvalue", RspValues.SOIL_HK.getValues());
        }
    }),

    TEMPHUMI_HK("temphumiHK",new HashMap<String, Object>(){
        {
            put("type","temphumi_hk");
            put("fcode", 3);
            put("reg",  new int[]{0});
            put("len", 2);
            put("rspvalue", RspValues.TEMPHUMI_HK.getValues());
        }
    }),

    ILLU_HK("illuHK",new HashMap<String, Object>(){
        {
            put("type","illu_hk");
            put("fcode", 3);
            put("reg",  new int[]{0});
            put("len", 2);
            put("rspvalue", RspValues.ILLU_HK.getValues());
        }
    }),

    CO2_HK("co2HK",new HashMap<String, Object>(){
        {
            put("type","co2_hk");
            put("fcode", 3);
            put("reg",  new int[]{0});
            put("len", 1);
            put("rspvalue", RspValues.CO2_HK.getValues());
        }
    }),

    DO_ZN("doZN",new HashMap<String, Object>(){
        {
            put("type","do_zn");
            put("fcode", 4);
            put("reg",  new int[]{1});
            put("len", 1);
            put("rspvalue", RspValues.DO_ZN.getValues());
        }
    }),

    PH_ZN("phZN",new HashMap<String, Object>(){
        {
            put("type","combo_ph_zn");
            put("fcode", 4);
            put("reg",  new int[]{1});
            put("len", 2);
            put("rspvalue", RspValues.PH_ZN.getValues());
        }
    }),

    DO_WS("doWS",new HashMap<String, Object>(){
        {
            put("type","combo_do_ws");
            put("fcode", 3);
            put("reg",  new int[]{0});
            put("len", 2);
            put("rspvalue", RspValues.DO_WS.getValues());
        }
    }),

    PH_WS("phWS",new HashMap<String, Object>(){
        {
            put("type","ph_ws");
            put("fcode", 3);
            put("reg",  new int[]{1});
            put("len", 1);
            put("rspvalue", RspValues.PH_WS.getValues());
        }
    }),

    DOPH_YAG("comboYAG",new HashMap<String, Object>(){
        {
            put("type","combo_yag");
            put("fcode", 3);
            put("reg",  new int[]{0x0401,0x0501,0x0601,0x0701,0x0801,0x0901,0x0a01});
            put("len", 3);
            put("rspvalue", RspValues.COMBO_YAG.getValues());
        }
    }),








    /**
     * 继电器指令
     */
    DMA_STA("dma",new HashMap<String, Object>(){
        {
            put("type","relaysta_dma");
            put("fcode", 1);
            put("reg",  new int[]{0});
            put("len", 8);
            put("rspvalue", RspValues.DMA_STA.getValues());
        }
    }),

    /**
     * 继电器指令
     */
    DMA_CTL("dmaCTL",new HashMap<String, Object>(){
        {
            put("type", "ctl_dma");
            put("fcode", 5);
            put("road", 8);
            put("on", 65280);
            put("off", 0);
        }
    })
    ;
    private String type;
    private Map<String,Object> order;


    SensorOrderEnum(String type, Map<String,Object> order) {
        this.type = type;
        this.order = order;

    }

    public static Map getOrder(String type){
        for(SensorOrderEnum s:SensorOrderEnum.values()){
            if(type.equalsIgnoreCase(s.getType())){
                return s.getOrder();
            }
        }
        return null;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getOrder() {
        return order;
    }

    public void setOrder(Map<String, Object> order) {
        this.order = order;
    }
}

