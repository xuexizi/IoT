package com.jit.iot.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @className: RelayStatusDO
 * @author: kay
 * @date: 2019/7/22 15:51
 * @packageName: com.jit.iot.domain
 */
@Data
@TableName("relaystatus")
public class RelayStatusDO {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Integer gw_id;
    private Integer addr;
    private byte position;
    private byte onofflg;
    private short value;
    private Date time;

    public RelayStatusDO(Integer gwId, Integer addr, byte position, byte onofflg, short value, Date time) {
        this.gw_id = gwId;
        this.addr = addr;
        this.position = position;
        this.onofflg = onofflg;
        this.value = value;
        this.time = time;
    }

    public RelayStatusDO(Long id, Integer gwId, Integer addr, Integer position, Integer onofflg, Integer value, Timestamp time) {
        this.id = id;
        this.gw_id = gwId;
        this.addr = addr;
        this.position = (byte)(position&0xff);
        this.onofflg =(byte) (onofflg&0xff);
        this.value = (short) (value&0xffff);
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getGwId() {
        return gw_id;
    }

    public void setGwId(Integer gwId) {
        this.gw_id = gwId;
    }

    public Integer getAddr() {
        return addr;
    }

    public void setAddr(Integer addr) {
        this.addr = addr;
    }

    public byte getPosition() {
        return position;
    }

    public void setPosition(byte position) {
        this.position = position;
    }

    public byte getOnofflg() {
        return onofflg;
    }

    public void setOnofflg(byte onofflg) {
        this.onofflg = onofflg;
    }

    public short getValue() {
        return value;
    }

    public void setValue(short value) {
        this.value = value;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
