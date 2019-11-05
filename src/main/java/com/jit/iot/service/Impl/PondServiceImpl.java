package com.jit.iot.service.Impl;

import com.jit.iot.entry.Pond;
import com.jit.iot.mapper.PondMapper;
import com.jit.iot.service.PondService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

import java.util.List;

/**
 * @packageName: com.jit.iot.service.Impl
 * @className: PondServiceImpl
 * @Description:
 * @author: xxz
 * @date: 2019/7/25 10:12
 */

@Service
public class PondServiceImpl implements PondService {

    @Resource
    PondMapper pondMapper;

    //用户新增塘口
    public String add_pond(int user_id, double length, double width, double longitude,
                    double latitude, String type, String pond_name, int gw_id) {
        int ret = pondMapper.insertPond(length, width, longitude, latitude, type, pond_name, user_id, gw_id);
        if(ret < 0){
            return "fail";
        } else {
            return "success";
        }
    }

    //用户查询塘口信息
    public List<Pond> pond_list(int user_id) {
        return pondMapper.getPondsInfo(user_id);
    }

}
