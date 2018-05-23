package com.hlzj.test.dao;

import com.hlzj.test.entity.Oxygen;
import com.hlzj.test.entity.OxygenExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OxygenMapper {
    int countByExample(OxygenExample example);

    int deleteByExample(OxygenExample example);

    int insert(Oxygen record);

    int insertSelective(Oxygen record);

    List<Oxygen> selectByExample(OxygenExample example);

    int updateByExampleSelective(@Param("record") Oxygen record, @Param("example") OxygenExample example);

    int updateByExample(@Param("record") Oxygen record, @Param("example") OxygenExample example);

    Oxygen selectLatest();
}