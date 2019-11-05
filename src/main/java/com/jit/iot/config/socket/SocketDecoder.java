package com.jit.iot.config.socket;
import java.nio.charset.Charset;

import com.jit.iot.utils.tlv.TLV;
import com.jit.iot.utils.tlv.TLVTools;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

/**
 * @className: SocketDecoder
 * @author: kay
 * @date: 2019/7/16 21:37
 * @packageName: com.jit.iot.config.socket
 */
public class SocketDecoder extends CumulativeProtocolDecoder {
    /**编码集合*/
    private final Charset charset;

    public SocketDecoder(Charset charset) {
        this.charset = charset;
    }


    @Override
    protected boolean doDecode(IoSession session, IoBuffer message, ProtocolDecoderOutput out) throws Exception {

        byte[] data = new byte[message.remaining()];
        // String meg=new String(data);
        TLVTools  tlvTools = new TLVTools();
        TLV tlv;
        tlvTools.unpack(data);

        for(int i=0;i<tlvTools.tlvList.size();i++)
        {
            tlv = tlvTools.tlvList.get(i);
            System.out.print("   标签:["+i+"]"+"\n");
            System.out.print("  tag:"+tlv.getTag()+"\n");
            System.out.print("  len:"+tlv.getLen()+"\n");
            System.out.print("value:"+ tlvTools.bytesToHex(tlv.getValue())+"\n");
        }
        //byte[] -> int int result = ByteBuffer.wrap(bytes).getInt();
        //int -> byte[] byte[] bytes = ByteBuffer.allocate(4).putInt(1695609641).array();qian'tao
        return true;

    }

}
