package com.base.wang.service;

import com.base.wang.entity.BasUser;

/**
 * Created by wxb on 2018/12/28.
 */
public interface BasUserService extends BaseService<BasUser>{
    /**
     * 用户注册
     */
    Object register(String userName, String password);
}
