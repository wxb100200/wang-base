package com.base.wang.controller;

import com.base.wang.common.PageReturn;
import com.base.wang.entity.BasAccount;
import com.base.wang.service.BasAccountService;
import com.base.wang.service.BasUserService;
import com.base.wang.util.MD5Util;
import com.base.wang.util.StringUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.NoSuchAlgorithmException;

/**
 * 用户登陆
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    BasAccountService accountService;
    @Autowired
    BasUserService userService;

    /**
     * 用户登陆
     */
    @ResponseBody
    @RequestMapping(value="/login")
    public Object login(String userName,String password){
        //用户名密码不能为空
        if(StringUtil.isEmpty(userName)|| StringUtil.isEmpty(password)){
            return PageReturn.fail("用户名或密码不能为空");
        }
        //查找用户账号
        BasAccount account=accountService.findByUserName(userName);
        String salt=account.getSalt();
        try {
            String encryPassword= MD5Util.MD5(password+salt);//加密密码
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(userName, encryPassword);
            subject.login(token);
            return PageReturn.success("登陆成功");
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            return PageReturn.success("用户名错误！");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            return PageReturn.success("密码错误");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return PageReturn.success("MD5加密异常");
        }
    }

    /**
     * 注册
     */
    @ResponseBody
    @RequestMapping(value="/register")
    public Object register(  String userName,   String password){
        //用户名密码不能为空
        if(StringUtil.isEmpty(userName)|| StringUtil.isEmpty(password)){
            return PageReturn.fail("用户名或密码不能为空");
        }
        return userService.register(userName,password);
    }
}
