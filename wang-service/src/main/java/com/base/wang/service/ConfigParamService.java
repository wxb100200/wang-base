package com.base.wang.service;

import com.base.wang.entity.BasConfigParam;

import java.util.List;

/**
 * Created by wxb on 2018/12/28.
 */
public interface ConfigParamService extends BaseService<BasConfigParam>{
    /**
     * 查询所有配置数据
     */
    List<BasConfigParam> findAllConfig();
}
