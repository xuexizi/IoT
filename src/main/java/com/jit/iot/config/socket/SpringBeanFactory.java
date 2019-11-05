package com.jit.iot.config.socket;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import static com.jit.iot.IotApplication.logger;

/**
 * @className: SpringBeanFactory
 * @author: kay
 * @date: 2019/7/22 15:36
 * @packageName: com.jit.iot.config.socket
 */
@Service
public class SpringBeanFactory implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringBeanFactory.applicationContext = applicationContext;
    }

    /**
     * 获取某个Bean的对象
     */
    public static <T> T getBean(Class<T> clazz) {
        try {
            return applicationContext.getBean(clazz);
        } catch (Exception e) {
            logger.error("Spring getBean:{},e:{}" ,clazz, e);
        }
        return null;
    }
}
