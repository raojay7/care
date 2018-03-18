package com.hlzj.test.utils;

import com.hlzj.test.entity.Temperature;

import java.util.Date;

public class StringSpiltUtil
{
    public static Temperature spiltTemperature(String string){
        String[] split = string.split(" ");
        String type=split[0];
        Double data=Double.parseDouble(split[1]);
        Temperature temperature=new Temperature();
        temperature.setTemperature(data);
        temperature.setCdate(new Date());
        return temperature;
    }
}
