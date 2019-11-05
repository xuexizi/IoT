package com.jit.iot.utils.hardware;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * @className: RegisterReplyData
 * @author: kay
 * @date: 2019/7/22 15:53
 * @packageName: com.jit.iot.utils.hardware
 */
@Data
@AllArgsConstructor
public class RegisterReplyData {
    private String terminal;//终端类型
    private Integer id;//网关ID
    private String msgType;//消息类型
    private List<Map> sensors;
    private List<Map> relays;
}
