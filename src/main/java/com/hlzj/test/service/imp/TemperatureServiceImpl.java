package com.hlzj.test.service.imp;

import com.hlzj.test.dao.TemperatureMapper;
import com.hlzj.test.entity.Temperature;
import com.hlzj.test.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TemperatureServiceImpl implements TemperatureService{
    @Autowired
    TemperatureMapper temperatureMapper;

    @Override
    public void insert(int id, double temperature, Date cdate) {
        Temperature record = new Temperature();
        record.setId(id);
        record.setTemperature(temperature);
        record.setCtime(cdate);
        temperatureMapper.insert(record);
    }

    @Override
    public double getLatest() {
        Temperature temperature = temperatureMapper.selectLatest();
        if(temperature != null)
            return temperature.getTemperature();
        return 0.0;
    }
    @Override
    public void dataInsert(int id, String symbol, String realData){
        Temperature record = new Temperature();
        record.setCtime(new Date());
        record.setId(id);
        switch (symbol){
            case "T01":
                record.setTemperature(Double.valueOf(realData));
                if(temperatureMapper == null)System.out.println("mapper is null o!");
                temperatureMapper.insert(record);
                break;
        }
    }

}
