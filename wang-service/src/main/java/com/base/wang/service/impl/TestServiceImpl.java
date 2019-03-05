package com.base.wang.service.impl;

import com.base.wang.common.PageList;
import com.base.wang.common.PageReturn;
import com.base.wang.common.Paginator;
import com.base.wang.entity.BasTest;
import com.base.wang.mapper.BasTestMapper;
import com.base.wang.service.JedisClient;
import com.base.wang.service.TestService;
import com.base.wang.util.JsonUtil;
import com.base.wang.util.StringUtil;
import com.github.pagehelper.PageHelper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wxb on 2018/12/21.
 */
@Service("testService")
public class TestServiceImpl extends BaseServiceImpl<BasTest> implements TestService {
    private Logger log = Logger.getLogger(this.getClass());

    @Autowired
    private BasTestMapper testMapper;
    @Autowired
    private JedisClient jedisClient;

    public BasTest findById(Integer id) {
        String dataStr=jedisClient.hget("wangxiaobing",id+"");
        if(StringUtil.isEmpty(dataStr)){
            System.out.println("--->>>>1111");
            BasTest test= testMapper.findById(id);
            if(test==null)return null;
            jedisClient.hset("wangxiaobing",id+"",JsonUtil.toJson(test));
            return test;
        }else {
            System.out.println("--->>>>22222");
            BasTest test= JsonUtil.json2Object(dataStr,BasTest.class);
            return test;
        }

    }
    public BasTest findById2(Integer id) {
        return testMapper.selectByPrimaryKey(id);
    }

    public Object pageHelper(Paginator p) {
        PageHelper.startPage(p.getPageNum(), p.getPageSize());
        List<BasTest> testList=testMapper.selectAll();
        return new PageList(testList);
    }

    public Object log4j() {
        log.debug("---->>>>>>>>>log4j debug:");
        log.info("---->>>>>>>>>log4j info:");
        log.warn("---->>>>>>>>>log4j warn:");
        log.error("---->>>>>>>>log4j error:");
        return PageReturn.success();
    }

    public List<BasTest> findAll(){
        return testMapper.selectAll();
    }
}
