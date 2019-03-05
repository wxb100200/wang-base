package com.base.wang.controller;

import com.base.wang.entity.BasTest;
import com.base.wang.service.TestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by wxb on 2019/3/5.
 */
@RestController
@RequestMapping("/redis")
public class RedisController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private TestService testService;
    @ResponseBody
    @RequestMapping("/findById")
    public Object findById(Integer id){
        BasTest test = testService.findById(id);
        return test;
    }
}
