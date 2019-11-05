package com.jit.iot.utils.hardware;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import static com.jit.iot.IotApplication.logger;
import java.io.IOException;

/**
 * @className: JacksonUtils
 * @author: kay
 * @date: 2019/7/22 15:18
 * @packageName: com.jit.iot.utils
 */

public class JacksonUtils {
    public static ObjectMapper objectMapper = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

    /**
     * 使用泛型方法,把json字符串转转为相应的javabean对象
     */
    public static <T> T readValue(String jsonStr, Class<T> valueType) throws IOException {
        return objectMapper.readValue(jsonStr,valueType);
    }


    /**
     * 把jaavabean转换为json字符串
     */
    public static String toJson(Object object){
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            logger.error("toJson is error :{}",e);
        }
        return null;
    }
}
