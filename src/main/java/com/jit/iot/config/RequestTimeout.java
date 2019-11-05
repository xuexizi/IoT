package com.jit.iot.config;

import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveRequestTimeoutHandler;

import static com.jit.iot.IotApplication.logger;

/**
 * @className: RequestTimeout
 * @author: kay
 * @date: 2019/7/18 8:56
 * @packageName: com.jit.iot.config
 */
public class RequestTimeout implements KeepAliveRequestTimeoutHandler {

    private int timeoutNum = 300000;

    public void keepAliveRequestTimedOut(KeepAliveFilter keepAliveFilter, IoSession session) throws Exception {
        Integer num = (Integer) session.getAttribute(keepAliveFilter.getRequestTimeout());
        if (num == null || num >= timeoutNum){
            logger.warn("{} 会话关闭了!",session.getRemoteAddress().toString().replace("/",""));
            session.closeOnFlush();
        }
    }
}
