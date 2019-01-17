package com.base.wang.service;

import com.base.wang.common.Paginator;
import com.base.wang.entity.BasTest;

import java.util.List;

/**
 * Created by wxb on 2018/12/4.
 */

public interface TestService extends BaseService<BasTest> {

     BasTest findById(Integer id);

     BasTest findById2(Integer id);

     Object pageHelper(Paginator p) ;

     Object log4j() ;

     List<BasTest> findAll();
}
