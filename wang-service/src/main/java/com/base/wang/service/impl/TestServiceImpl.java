package com.base.wang.service.impl;

import com.base.wang.common.PageList;
import com.base.wang.common.PageReturn;
import com.base.wang.common.Paginator;
import com.base.wang.entity.BasTest;
import com.base.wang.mapper.BasTestMapper;
import com.base.wang.service.TestService;
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
    BasTestMapper testMapper;

    public BasTest findById(Integer id) {
        return testMapper.findById(id);
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
