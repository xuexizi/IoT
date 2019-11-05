package com.jit.iot.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jit.iot.domain.CommonDataDO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @className: CommonDataMapper
 * @author: kay
 * @date: 2019/7/22 17:16
 * @packageName: com.jit.iot.mapper
 */
@Mapper
public interface CommonDataMapper extends BaseMapper<CommonDataDO> {
    void insertBatch(@Param("datas") List<CommonDataDO> datas);
}

