package com.hlzj.test.service.imp;

import com.hlzj.test.dao.OxygenMapper;
import com.hlzj.test.entity.Oxygen;
import com.hlzj.test.entity.Temperature;
import com.hlzj.test.service.OxygenService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class OxygenServiceImpl implements OxygenService
{
    @Autowired
    OxygenMapper oxygenMapper;

    @Override
    public void insert(int id, Double oxygendata, Date cdate)
    {
        Oxygen oxygen=new Oxygen();
        oxygen.setId(id);
        oxygen.setOxygendata(oxygendata);
        oxygen.setCdate(cdate);
        oxygenMapper.insert(oxygen);
    }

    @Override
    public Double getLatest()
    {
        Oxygen oxygen = oxygenMapper.selectLatest();
        if(oxygen != null)
            return oxygen.getOxygendata();
        return 0.0;
    }
}
