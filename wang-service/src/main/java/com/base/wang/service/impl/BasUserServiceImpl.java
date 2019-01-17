package com.base.wang.service.impl;

import com.base.wang.common.MyException;
import com.base.wang.common.PageReturn;
import com.base.wang.entity.BasAccount;
import com.base.wang.entity.BasUser;
import com.base.wang.mapper.BasAccountMapper;
import com.base.wang.service.BasUserService;
import com.base.wang.util.MD5Util;
import com.base.wang.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;

/**
 * Created by wxb on 2018/12/21.
 */
@Service
public class BasUserServiceImpl extends BaseServiceImpl<BasUser> implements BasUserService {
    @Autowired
    BasAccountMapper accountMapper;

    /**
     * 用户注册
     */
    public Object register(String userName, String password) {
        //判断该用户是否已经存在
        BasAccount account=accountMapper.findByUserName(userName);
        if(account!=null){
            return PageReturn.fail("该用户已经被注册");
        }
        //新增账号
        String salt= RandomUtil.generateRandomChar(4);//获得随机盐
        String encryPassword= null;//加密密码
        try {
            encryPassword = MD5Util.MD5(password+salt);
        } catch (NoSuchAlgorithmException e) {
            throw  new MyException("注册MD5加密异常");
        }
        account=new BasAccount();
        account.setUserName(userName);
        account.setPassword(encryPassword);
        account.setSalt(salt);
        accountMapper.insert(account);
        return PageReturn.success("注册成功");
    }
}
