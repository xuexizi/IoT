package com.jit.iot.config;

import com.jit.iot.config.socket.KeepAliveMessageFactoryImpl;
import com.jit.iot.config.socket.ServerHandler;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.textline.TextLineCodecFactory;
import org.apache.mina.filter.keepalive.KeepAliveFilter;
import org.apache.mina.filter.keepalive.KeepAliveMessageFactory;
import org.apache.mina.filter.logging.LoggingFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static com.jit.iot.IotApplication.logger;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;
import com.jit.iot.config.socket.SocketFactory;


/**
 * @className: MinaConfig
 * @author: kay
 * @date: 2019/7/16 21:25
 * @packageName: com.jit.iot.config
 */
@Configuration
public class MinaConfig {
    // socket占用端口
    @Value("${mina.port}")
    private int port;

    /** 15秒发送一次心跳包 */
    private static final int HEARTBEATRATE = 15;

    @Bean
    public LoggingFilter loggingFilter() {
        return new LoggingFilter();
    }

    @Bean
    public IoHandler ioHandler() { return new ServerHandler(); }

    @Bean
    public InetSocketAddress inetSocketAddress() {
        return new InetSocketAddress(port);
    }

    @Bean
    public IoAcceptor ioAcceptor() throws Exception {
        IoAcceptor acceptor = new NioSocketAcceptor();
        acceptor.getFilterChain().addLast("logger", loggingFilter());
        acceptor.getFilterChain().addLast("coderc",   // 使用自定义编码解码工厂类
                new ProtocolCodecFilter(new TextLineCodecFactory(Charset.forName("UTF-8"))));//设置编码过滤器
        acceptor.getSessionConfig().setReadBufferSize(1024*1024);//设置缓冲区
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);  //配置会话信息

        //心跳设置
////        KeepAliveMessageFactory heartBeatFactory = new KeepAliveMessageFactoryImpl();
////        KeepAliveFilter heartBeat = new KeepAliveFilter(heartBeatFactory,IdleStatus.BOTH_IDLE);
////        heartBeat.setForwardEvent(true); //设置是否forward到下一个filter
////        heartBeat.setRequestInterval(HEARTBEATRATE);        //设置心跳频率
////        heartBeat.setRequestTimeoutHandler(new RequestTimeout());//设置心跳失败
////        acceptor.getFilterChain().addLast("heartbeat", heartBeat);

        //心跳检测结束
        acceptor.setHandler(ioHandler()); //自定义处理业务的代码：自定义的类
        acceptor.bind(inetSocketAddress());//绑定端口号
        logger.info("Socket服务器在端口：" + port + "已经启动");

        return acceptor;
    }
}

