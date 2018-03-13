package com.hlzj.test.controller;

import com.alibaba.druid.sql.visitor.functions.If;
import com.hlzj.test.entity.CareResult;
import com.hlzj.test.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("bed_control")
    @ResponseBody
    public CareResult test(String instruction){
        CareResult result=new CareResult();
        result.setStatus(200);
        if(instruction.isEmpty()){
            result.setStatus(400);
            return result;
        }
        System.out.println(instruction);
        return result;
    }

    @RequestMapping("/{path}")
    public String showPath(@PathVariable  String path){
        return path;
    }

}
