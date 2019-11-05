package com.jit.iot.utils.hardware;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @className: ReportData
 * @author: kay
 * @date: 2019/7/22 14:59
 * @packageName: com.jit.iot.utils
 */
@Data
@AllArgsConstructor
public class ReportData {
    private Integer addr;//传感器485地址
    private Integer reg;//传感器485地址
    private String type;//传感器类型
    private Float value;//检测值（使用list的原因：电压电流上报三个值，其他一个值）
}
