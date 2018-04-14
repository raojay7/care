package com.hlzj.test.service;

import com.hlzj.test.dao.TemperatureMapper;
import com.hlzj.test.entity.Temperature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TestService
{
    @Autowired
    private TemperatureMapper temperatureMapper;

    public void test(){
        Temperature temperature = new Temperature();
        temperature.setId((int)(Math.random()*100));
        temperature.setCdate(new Date());
        temperature.setTemperature((Math.random()*500));
        temperatureMapper.insert(temperature);
        System.out.println("service test");
    }
}
