package com.jit.iot.utils.tlv;

/**
 * @className: TLV
 * @author: kay
 * @date: 2019/7/17 17:29
 * @packageName: com.jit.iot.utils
 */
public class TLV{
    int tag;//tag定义成数字是为了方便遍历使用
    int len;
    byte[] value;

    public int getTag() {
        return tag;
    }
    public void setTag(int tag) {
        this.tag = tag;
    }
    public int getLen() {
        return len;
    }
    public void setLen(int len) {
        this.len = len;
    }
    public byte[] getValue() {
        return value;
    }
    public void setValue(byte[] value) {
        this.value = value;
    }
}
