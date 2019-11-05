package com.jit.iot;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*
 * 启动类
 * @Author kay
 * @Date 21:22 2019/7/16
 **/

@SpringBootApplication
public class IotApplication {
    public static final Logger logger = LoggerFactory.getLogger(IotApplication.class);
    public static void main(String[] args) {
        SpringApplication.run(IotApplication.class, args);
    }

}
