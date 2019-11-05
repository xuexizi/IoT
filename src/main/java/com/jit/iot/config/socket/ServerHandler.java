package com.jit.iot.config.socket;
import static com.jit.iot.IotApplication.logger;

import com.jit.iot.service.AnalyzeService;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;


/**
 * @className: ServerHandler
 * @author: kay
 * @date: 2019/7/16 21:28
 * @packageName: com.jit.iot.handler
 */
public class ServerHandler extends IoHandlerAdapter {

    @Resource
    AnalyzeService analyzeService;

    @Override
    public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
        logger.error("出现异常 :" + session.getRemoteAddress().toString() + " : " + cause.toString());
        //session.write("出现异常");
        session.closeNow();

    }


    @Override
    public void sessionCreated(IoSession session) throws Exception {
        logger.info("连接创建 : " + session.getRemoteAddress().toString());
        session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        logger.info("连接打开: " + session.getRemoteAddress().toString());
    }

    @Override
    public void messageReceived(IoSession session, Object message) throws Exception {
        logger.info("接受到数据 :" + String.valueOf(message));
        String text = String.valueOf(message);

        analyzeService.AnalyzeData(session,message);

    }


    @Override
    public void messageSent(IoSession session, Object message) throws Exception {
        logger.info("返回消息内容 : " + message.toString());
        //session.closeNow(); //短连接
    }

    @Override
    public void sessionIdle(IoSession session, IdleStatus status) throws Exception {
        if (status == IdleStatus.READER_IDLE) {
            //logger.info("进入读空闲状态");
            //session.closeNow();
        } else if (status == IdleStatus.BOTH_IDLE) {
          //  logger.info("BOTH空闲");
            //session.closeNow();
        }
    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {
        logger.info("连接关闭 : " + session.getRemoteAddress().toString());
        int size = session.getService().getManagedSessions().values().size();
        logger.info("连接关闭时session数量==>>" + size);
    }



}

