package com.jit.iot.domain;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @className: CommonDataDO
 * @author: kay
 * @date: 2019/7/22 15:51
 * @packageName: com.jit.iot.domain
 */
@Data
@TableName("commondata")
public class CommonDataDO {
    @TableId(type = IdType.AUTO)
    private BigInteger id;
    private Integer gw_id;
    private Integer addr;
    private Integer reg;
    private String type;
    private Float value;
    private Date time;

    public CommonDataDO(Integer gwId, Integer addr,Integer reg, String type, Float value, Date time) {
        this.gw_id = gwId;
        this.addr = addr;
        this.reg  = reg;
        this.type = type;
        this.value = value;
        this.time = time;
    }

    public CommonDataDO(BigInteger id, Integer gwId, Integer addr, Integer reg, String type, Float value, Timestamp time) {
        this.id = id;
        this.gw_id = gwId;
        this.addr = addr;
        this.reg  = reg;
        this.type = type;
        this.value = value;
        this.time = time;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public Date getTime(){
        return time;
    }
}

