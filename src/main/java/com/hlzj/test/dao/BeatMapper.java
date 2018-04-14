package com.hlzj.test.dao;

import java.util.List;

import com.hlzj.test.entity.Beat;
import com.hlzj.test.entity.BeatExample;
import org.apache.ibatis.annotations.Param;

public interface BeatMapper {
    int countByExample(BeatExample example);

    int deleteByExample(BeatExample example);

    int insert(Beat record);

    int insertSelective(Beat record);

    List<Beat> selectByExample(BeatExample example);

    int updateByExampleSelective(@Param("record") Beat record, @Param("example") BeatExample example);

    int updateByExample(@Param("record") Beat record, @Param("example") BeatExample example);

    Beat selectLatest();
}