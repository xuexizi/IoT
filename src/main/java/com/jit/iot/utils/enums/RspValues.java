package com.jit.iot.utils.enums;

import java.util.HashMap;

/**
 * @className: RspValues
 * @author: kay
 * @date: 2019/7/22 15:56
 * @packageName: com.jit.iot.utils.enums
 */
public enum RspValues {

    DO_WS(new HashMap[]{
            new HashMap<String, Object>() {
                {
                    put("stype", "temp_ws");
                    put("value", new int[]{3, 4});
                    put("unit", 10);
                }
            },
            new HashMap<String, Object>() {
                {
                    put("stype", "do_ws");
                    put("value", new int[]{5, 6});
                    put("unit", 10);
                }
            },
    }),
    PH_WS(new HashMap[]{
            new HashMap<String, Object>() {
                {
                    put("stype", "ph_ws");
                    put("value", new int[]{3, 4});
                    put("unit", 100);
                }
            },
    }),
    DO_ZN(new HashMap[]{
            new HashMap<String, Object>() {
                {
                    put("stype", "do_zn");
                    put("value", new int[]{3, 4});
                    put("unit", 100);
                }
            }
    }),
    PH_ZN(new HashMap[]{
            new HashMap<String, Object>() {
                {
                    put("stype", "ph_zn");
                    put("value", new int[]{3, 4});
                    put("unit", 100);
                }
            },
            new HashMap<String, Object>() {
                {
                    put("stype", "temp_zn");
                    put("value", new int[]{5, 6});
                    put("unit", 100);
                }
            },
    }),

    TEMPHUMI_HK(new HashMap[]{
            new HashMap<String, Object>() {
                {
                    put("stype", "temp");
                    put("value", new int[]{3, 4});
                    put("unit", 10);
                }
            },
            new HashMap<String, Object>() {
                {
                    put("stype", "humi");
                    put("value", new int[]{5, 6});
                    put("unit", 10);
                }
            },
    }),
    ILLU_HK(new HashMap[]{
            new HashMap<String, Object>() {
                {
                    put("stype", "illu");
                    put("value", new int[]{3, 4, 5, 6});
                    put("unit", 1);
                }
            }
    }),
    CO2_HK(new HashMap[]{
            new HashMap<String, Object>() {
                {
                    put("stype", "co2");
                    put("value", new int[]{3, 4});
                    put("unit", 1);
                }
            }
    }),
    LIQUID_POS(new HashMap[]{
            new HashMap<String, Object>() {
                {
                    put("stype", "liquidLevel");
                    put("value", new int[]{3, 4});
                    put("unit", 10);
                }
            }
    }),


    SOIL_HK(new HashMap[]{
            new HashMap<String, Object>() {
                {
                    put("stype", "soilTemp");
                    put("value", new int[]{3, 4});
                    put("unit", 10);
                }},
            new HashMap<String, Object>() {
                {
                    put("stype", "soilMoist");
                    put("value", new int[]{5, 6});
                    put("unit", 10);
                }},
            new HashMap<String, Object>() {
                {
                    put("stype", "soilTDS");
                    put("value", new int[]{7, 8});
                    put("unit", 1);
                }
            },
            new HashMap<String, Object>() {
                {
                    put("stype", "soilEC");
                    put("value", new int[]{9, 10});
                    put("unit", 1);
                }
            }
    }),

    COMBO_YAG(new HashMap[]{
            new HashMap<String, Object>() {
                {
                    put("stype", "do_yag");
                    put("value", new int[]{3, 4});
                    put("unit", 100);
                }},
            new HashMap<String, Object>() {
                {
                    put("stype", "temp_yag");
                    put("value", new int[]{5, 6});
                    put("unit", 10);
                }},
            new HashMap<String, Object>() {
                {
                    put("stype", "ph_yag");
                    put("value", new int[]{7, 8});
                    put("unit", 10);
                }
            }
    }),

    DMA_STA(new HashMap[]{
            new HashMap<String, Object>() {
                {
                    put("stype", "relay_sta_dma");
                    put("value", new int[]{3});
                    put("unit", 1);
                }
            }
    });



    private HashMap<String,Object> [] values;

    RspValues( HashMap<String, Object>[] values) {
        this.values = values;
    }

    public HashMap<String, Object>[] getValues() {
        return values;
    }

    public void setValues(HashMap<String, Object>[] values) {
        this.values = values;
    }
}
