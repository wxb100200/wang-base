package com.base.wang.controller;

import com.base.wang.entity.BasTest;
import com.base.wang.service.TestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Created by wxb on 2018/12/4.
 */
@Controller
@RequestMapping("/test")
public class TestController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private TestService testService;
    @ResponseBody
    @RequestMapping("/findById")
    public Object findById(){
        BasTest test = testService.findById(1);
        return test;
    }
}
