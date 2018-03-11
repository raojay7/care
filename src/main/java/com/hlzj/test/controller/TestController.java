package com.hlzj.test.controller;

import com.hlzj.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController
{

    @Autowired
    TestService testService;

    @RequestMapping("test")
    public String testPath(){

        testService.test();
        return "index";
    }
}
