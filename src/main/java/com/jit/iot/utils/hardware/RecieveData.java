package com.jit.iot.utils.hardware;
import lombok.Data;

import java.util.List;

/**
 * @className: RecieveData
 * @author: kay
 * @date: 2019/7/22 14:57
 * @packageName: com.jit.iot.utils
 */
@Data
public class RecieveData {
    private String terminal;//终端类型
    private Integer deviceID;//网关ID
    private String msgType;//消息类型
    //private String from; //APP的Socket
    private String to;//APP的Socket
    private String order;//指令：继电器addr+路+值（比如：#254#1#1）
    private String start; //历史数据查询起始时间 格式： 2019:04:12 12:00:00
    private String end; //历史数据查询终止时间
    private List<ReportData> content;//数据上报内容
}
