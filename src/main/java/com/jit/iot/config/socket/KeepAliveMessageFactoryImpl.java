package com.jit.iot.config.socket;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import static com.jit.iot.IotApplication.logger;
/**
 * @className: KeepAliveMessageFactoryImpl
 * @author: kay
 * @date: 2019/7/18 9:07
 * @packageName: com.jit.iot.config.socket
 */
public class KeepAliveMessageFactoryImpl implements KeepAliveMessageFactory {

    private static final String HEARTBEATREQUEST = "ping\r\n";
    private static final String HEARTBEATRESPONSE = "pong";

    @Override
    public boolean isRequest(IoSession ioSession, Object o) {
        logger.info("服务端判断消息是否为请求包请求："+o);
        if(o.equals(HEARTBEATREQUEST))
            return true;
        return false;
    }

    @Override
    public boolean isResponse(IoSession ioSession, Object o) {
        logger.info("服务端判断响应心跳包信息："+o);
        if(o.equals(HEARTBEATRESPONSE)){
            return true;
        }
        return false;
    }

    @Override
    public Object getRequest(IoSession ioSession) {
        logger.info("getRequest");
        return HEARTBEATREQUEST;
    }

    @Override
    public Object getResponse(IoSession ioSession, Object o) {
        logger.info("getResponse");
        return null;
    }
}
