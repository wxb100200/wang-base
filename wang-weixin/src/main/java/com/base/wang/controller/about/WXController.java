package com.base.wang.controller.about;

import com.base.wang.entity.BasTest;
import com.base.wang.service.TestService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wxb on 2018/12/27.
 */
@Controller
@RequestMapping("/wxChatServlet")
public class WXController {
    private Logger log = Logger.getLogger(this.getClass());
    @Autowired
    private TestService testService;
    @ResponseBody
    @RequestMapping("/wxChatServlet")
    public Object wxChatServlet(){
        log.info("-->>>>>>111111111111111111111111111");
        return null;
    }
}
