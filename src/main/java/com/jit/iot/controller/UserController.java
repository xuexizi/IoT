package com.jit.iot.controller;

import com.alibaba.fastjson.JSONObject;
import com.jit.iot.service.UserService;
import com.jit.iot.utils.redis.RedisUtil;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

/**
 * @className: UserController
 * @author: kay
 * @date: 2019/7/19 15:56
 * @packageName: com.jit.iot.controller
 */
@RestController
@CrossOrigin
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    private RedisUtil redisUtil;


    //登录
    @RequestMapping(value = "/login_req", method = RequestMethod.POST)
    public JSONObject UserLogin(@RequestParam(value = "phone") String phone,
                                @RequestParam(value = "password") String password,
                                HttpServletResponse httpServletResponse) {

//        String token = UUID.randomUUID().toString().substring(0, 15);
//        //缓存保留10分钟
//        /***********************************************/
//        redisUtil.set(token, tel, 600);
//        httpServletResponse.addHeader("token", token);

        return userService.login(phone, password);
    }

    //注册
    @RequestMapping(value = "/register_req", method = RequestMethod.POST)
    public String UserRegister(@RequestParam(value = "username") String username,
                           @RequestParam(value = "password") String password,
                           @RequestParam(value = "phone") String phone,
                           HttpServletResponse httpServletResponse) throws Exception{

        System.out.println("用户注册=========");
        System.out.println("username:" + username);
        System.out.println("password:" + password);
        System.out.println("phone:" + phone);

        //调用service
        return userService.register(username, password, phone);
    }

}
