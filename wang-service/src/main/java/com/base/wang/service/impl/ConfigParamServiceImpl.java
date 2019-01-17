package com.base.wang.service.impl;



import com.base.wang.entity.BasConfigParam;
import com.base.wang.mapper.BasConfigParamMapper;
import com.base.wang.service.ConfigParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by YScredit on 2018/8/14.
 */
@Service("configParamService")
public class ConfigParamServiceImpl extends BaseServiceImpl<BasConfigParam> implements ConfigParamService {

    @Autowired
    BasConfigParamMapper configParamMapper;
    /**
     * 查询所有配置数据
     */
    public List<BasConfigParam> findAllConfig(){
        List<BasConfigParam> list=configParamMapper.selectAll();
        return list;
    }
}
