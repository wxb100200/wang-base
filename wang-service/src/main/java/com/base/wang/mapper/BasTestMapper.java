package com.base.wang.mapper;

import com.base.wang.entity.BasTest;
import tk.mybatis.mapper.common.Mapper;

public interface BasTestMapper extends Mapper<BasTest> {

    BasTest findById(Integer id);
}