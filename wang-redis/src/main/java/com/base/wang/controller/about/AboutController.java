package com.base.wang.controller.about;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wxb on 2018/12/4.
 */
@Controller
@RequestMapping("/web")
public class AboutController {

    @RequestMapping("/test")
    public Object test() {
        return "test";
    }

    @RequestMapping("/login")
    public Object login() {
        return "login";
    }

    @RequestMapping("/register")
    public Object register() {
        return "register";
    }

    @RequestMapping("/index")
    public Object index() {
        return "index";
    }
}
