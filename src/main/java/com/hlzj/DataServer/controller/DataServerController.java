package com.hlzj.DataServer.controller;

import com.hlzj.DataServer.service.DataServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class DataServerController {
    @Autowired
    DataServerService dataServerService;

    @RequestMapping("StartServer")
    public String startServer(HttpSession session){
        session.setAttribute("temperature", "on");
        dataServerService.startListening(46666, session);
        return "tableUI";
    }

    @RequestMapping("ShutDown")
    public String shutDown(HttpSession session){
        session.setAttribute("temperature", "off");
        dataServerService.shutDown(session);
        return "tableUI";
    }

    @RequestMapping("send")
    public void sendToClient(HttpSession session, HttpServletResponse response, String order) throws IOException {
        System.out.println("send server");
        response.getWriter().println(order);
        dataServerService.send(session, order);
    }
}
