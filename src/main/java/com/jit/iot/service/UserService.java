package com.jit.iot.service;

import com.alibaba.fastjson.JSONObject;

/**
 * @className: UserService
 * @author: kay
 * @date: 2019/7/19 16:33
 * @packageName: com.jit.iot.service
 */
public interface UserService {

    //登录
    public JSONObject login(String phone, String password);

    //注册
    public String register(String username, String password, String phone);
}
