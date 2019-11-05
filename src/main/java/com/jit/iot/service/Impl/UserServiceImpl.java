package com.jit.iot.service.Impl;

import com.alibaba.fastjson.JSONObject;
import com.jit.iot.entry.User;
import com.jit.iot.mapper.UserMapper;
import com.jit.iot.service.UserService;
import com.jit.iot.utils.PhoneUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import static com.jit.iot.IotApplication.logger;

/**
 * @className: UserServiceImpl
 * @author: xxz
 * @date: 2019/7/25 8:52
 * @packageName: com.jit.iot.service.Impl
 */

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserMapper userMapper;


    //登录
    public JSONObject login(String phone, String password) {
        JSONObject resObj = new JSONObject();

        User user = userMapper.findByTel(phone);

        if (user == null) {
            logger.info("登录：" + phone + " 用户不存在");
            resObj.put("msg", "user_not_exist");
            return resObj;
        } else if (user.getPassword().equals(password)) {
            logger.info("登录：" + phone + " 登录成功！");
            resObj.put("msg", "success");
            resObj.put("user_id", user.getUser_id());
            resObj.put("username", user.getUsername());
            return resObj;
        } else {
            logger.info("登录：" + phone + " 密码错误！");
            resObj.put("msg", "password_error");
            return resObj;
        }
    }

    //注册
    public String register(String username, String password, String phone) {
        //检查电话是否合法
        boolean flag = PhoneUtils.isMobile(phone);
        if(!flag) {
            return "tel_illegal";
        }

        //检查电话是否被注册过
        User user = userMapper.findByTel(phone);
        if (user != null) {
            logger.info("注册：" + phone + " 手机号存在");
            return "tel_exist";
        } else {
            int ret = userMapper.insertUser(username, password, phone);
            if(ret < 0) {
                return "database_error";
            }else{
                return "success";
            }
        }
    }


}
