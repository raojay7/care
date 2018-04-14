package com.hlzj.test.dao;

import java.util.List;

import com.hlzj.test.entity.Temperature;
import com.hlzj.test.entity.TemperatureExample;
import org.apache.ibatis.annotations.Param;

public interface TemperatureMapper {
    int countByExample(TemperatureExample example);

    int deleteByExample(TemperatureExample example);

    int insert(Temperature record);

    int insertSelective(Temperature record);

    List<Temperature> selectByExample(TemperatureExample example);

    int updateByExampleSelective(@Param("record") Temperature record, @Param("example") TemperatureExample example);

    int updateByExample(@Param("record") Temperature record, @Param("example") TemperatureExample example);

    Temperature selectLatest();
}