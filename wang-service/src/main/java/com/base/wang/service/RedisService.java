package com.base.wang.service;

import com.base.wang.entity.BasTest;

/**
 * Created by wxb on 2019/3/5.
 */
public interface RedisService extends BaseService<BasTest>{

    BasTest findById(Integer id);

    Object initStores();//初始化商品

    Object rushToBuy();//抢购


}
