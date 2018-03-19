package com.hlzj.test.controller;

import com.hlzj.test.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

@Controller
public class TemperatureController {
    @Autowired
    TemperatureService temperatureService;

    @RequestMapping("insert")
    public void iiii(){
        temperatureService.insert(1, 3.5, new Date());
    }
}
