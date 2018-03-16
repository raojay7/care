package com.hlzj.test.dao;

import com.hlzj.test.entity.Temperature;

public interface TemperatureMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Temperature record);

    int insertSelective(Temperature record);

    Temperature selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Temperature record);

    int updateByPrimaryKey(Temperature record);
}