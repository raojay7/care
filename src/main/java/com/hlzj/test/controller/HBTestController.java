package com.hlzj.test.controller;

import com.hlzj.test.service.BeatService;
import com.hlzj.test.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.io.Writer;

@Controller
public class HBTestController {
    @Autowired
    private TemperatureService temperatureService;

    @Autowired
    BeatService beatService;
    @RequestMapping("hb")
    public String getHeartBeat(){
        return "test";
    }

    @RequestMapping("Beat")
    public void getBeat(HttpSession session, HttpServletResponse response) throws Exception {
        String temperatureStatus = (String)session.getAttribute("temperature");
        if(temperatureStatus != null)
            if(temperatureStatus.equals("on")) {
                String str=beatService.getLatest();
                if(str==null)return;
                response.getWriter().write(str);
                String[] split = str.split("#");
                Robot r   =   new   Robot();
                Writer writer=response.getWriter();
                for (String every:split){
                    writer.write(every);
                    writer.flush();
                    r.delay(10);
                }

            }else
                response.getWriter().write("0.0");
        else
            response.getWriter().write("0.0");
    }


    @RequestMapping("Temperature")
    public void getTemperature(HttpSession session, HttpServletResponse response) throws Exception {
        String temperatureStatus = (String)session.getAttribute("temperature");
        if(temperatureStatus != null)
            if(temperatureStatus.equals("on")) {
                Double temData=temperatureService.getLatest();
                if(temData==null)return;
                Writer writer=response.getWriter();
                writer.write(temData.toString());
                writer.flush();
            }else
                response.getWriter().write("0.0");
        else
            response.getWriter().write("0.0");
    }


}
