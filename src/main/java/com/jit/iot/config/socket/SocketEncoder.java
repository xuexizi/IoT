package com.jit.iot.config.socket;

import java.nio.charset.Charset;

import com.jit.iot.utils.tlv.TLV;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoderAdapter;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import org.apache.mina.core.buffer.IoBuffer;

/**
 * @className: SocketEncoder
 * @author: kay
 * @date: 2019/7/16 21:38
 * @packageName: com.jit.iot.config.socket
 */
public class SocketEncoder extends ProtocolEncoderAdapter {

    //编码
    private final Charset charset;

    public SocketEncoder(Charset charset) {
        //super();
        this.charset = charset;
    }

    // 编码 将数据包转成字节数组
    @Override
    public void encode(IoSession session, Object message, ProtocolEncoderOutput out) throws Exception {
        TLV msg = (TLV) message;
        IoBuffer buf = IoBuffer.allocate(msg.getLen());
        buf.setAutoExpand(true);
        buf.putInt(msg.getLen());
        buf.putInt(msg.getTag());
        if(msg.getLen() !=0){
            buf.put(msg.getValue());
        }
        buf.flip();
        out.write(buf);
    }
}
