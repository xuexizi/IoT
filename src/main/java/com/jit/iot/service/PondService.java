package com.jit.iot.service;

import com.jit.iot.entry.Pond;

import java.util.List;

public interface PondService {

    //用户新增塘口
    public String add_pond(int user_id, double length, double width, double longitude,
                    double latitude, String type, String pond_name, int gw_id);

    //用户查询塘口信息
    public List<Pond> pond_list(int user_id);

}
