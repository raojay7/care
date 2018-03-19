package com.hlzj.test.controller;

import com.hlzj.test.service.HBTestService;
import com.hlzj.test.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class HBTestController {
    @Autowired
    private TemperatureService temperatureService;

    @RequestMapping("hb")
    public String getHeartBeat(){
        return "test";
    }

    @RequestMapping("Beat")
    public void getBeat(HttpSession session, HttpServletResponse response) throws IOException {
        String temperatureStatus = (String)session.getAttribute("temperature");
        if(temperatureStatus != null)
            if(temperatureStatus.equals("on")) {
                response.getWriter().write(String.valueOf(temperatureService.getLatest()));
            }else
                response.getWriter().write("0.0");
        else
            response.getWriter().write("0.0");
    }

}
