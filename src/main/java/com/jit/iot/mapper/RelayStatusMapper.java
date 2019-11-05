package com.jit.iot.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jit.iot.domain.RelayStatusDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className: RelayStatusMapper
 * @author: kay
 * @date: 2019/7/22 17:16
 * @packageName: com.jit.iot.mapper
 */
@Mapper
public interface RelayStatusMapper extends BaseMapper<RelayStatusDO>  {

    void insertBatch(@Param("relays") List<RelayStatusDO> relays);
}
