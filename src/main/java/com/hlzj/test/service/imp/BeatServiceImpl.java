package com.hlzj.test.service.imp;

import com.hlzj.test.dao.BeatMapper;
import com.hlzj.test.entity.Beat;
import com.hlzj.test.service.BeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
@Service
public class BeatServiceImpl implements BeatService
{
    @Autowired
    private BeatMapper beatMapper;

    @Override
    public void insert(int id, String beatdata, Date cdate) {
        Beat record = new Beat();
        record.setId(id);
        record.setBeatdata(beatdata);
        record.setCdate(cdate);
        beatMapper.insert(record);
    }

    @Override
    public String getLatest() {
        Beat beat=beatMapper.selectLatest();
        if(beat != null)
            return beat.getBeatdata();
        else return null;
    }

}
