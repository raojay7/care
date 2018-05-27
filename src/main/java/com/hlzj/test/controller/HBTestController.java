package com.hlzj.test.controller;

import com.hlzj.test.service.BeatService;
import com.hlzj.test.service.OxygenService;
import com.hlzj.test.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.Writer;

@Controller
public class HBTestController {
    @Autowired
    private TemperatureService temperatureService;

    @Autowired
    private BeatService beatService;

    @Autowired
    private OxygenService oxygenService;

    @RequestMapping("/{path}")
    public String showPath(@PathVariable String path){
               return path;
          }
    @RequestMapping("hb")
    public String getHeartBeat(){
        return "test";
    }


    @RequestMapping("Beat")
    public void getBeat(HttpSession session, HttpServletResponse response) throws Exception {
        String temperatureStatus = (String)session.getAttribute("data");
        if(temperatureStatus != null)
            if(temperatureStatus.equals("on")) {
                String str=beatService.getLatest();
                if(str==null)return;
                String[] split = str.split("#");
                Writer writer=response.getWriter();
                writer.write(split[0]);
                writer.flush();

            }else
                response.getWriter().write("0.0");
        else
            response.getWriter().write("0.0");
    }


    @RequestMapping("Temperature")
    public void getTemperature(HttpSession session, HttpServletResponse response) throws Exception {
        String status = (String)session.getAttribute("data");
        if(status != null)
            if(status.equals("on")) {
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

    @RequestMapping("Oxygen")
    public void getOxygen(HttpSession session, HttpServletResponse response) throws Exception {
        String status = (String)session.getAttribute("data");
        if(status != null)
        {
            if (status.equals("on"))
            {
                Double oxygendata = oxygenService.getLatest();
                if (oxygendata == null)
                    return;
                Writer writer = response.getWriter();
                writer.write(oxygendata.toString());
                writer.flush();
            }
            else
                response.getWriter().write("-1.0");
        }
        else response.getWriter().write("-1.0");
    }



}
