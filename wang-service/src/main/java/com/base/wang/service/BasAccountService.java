package com.base.wang.service;

import com.base.wang.entity.BasAccount;

/**
 * Created by wxb on 2018/12/28.
 */
public interface BasAccountService extends BaseService<BasAccount>{
    /**
     * 根据用户名查找账号记录
     */
    BasAccount findByUserName(String userName);
}
