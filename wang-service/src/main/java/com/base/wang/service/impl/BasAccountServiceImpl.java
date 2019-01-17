package com.base.wang.service.impl;

import com.base.wang.entity.BasAccount;
import com.base.wang.mapper.BasAccountMapper;
import com.base.wang.service.BasAccountService;
import com.base.wang.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wxb on 2018/12/21.
 */
@Service
public class BasAccountServiceImpl extends BaseServiceImpl<BasAccount> implements BasAccountService {

    @Autowired
    BasAccountMapper accountMapper;
    /**
     * 根据用户名查找账号记录
     */
    public BasAccount findByUserName(String userName) {
        return accountMapper.findByUserName(userName);
    }
}
