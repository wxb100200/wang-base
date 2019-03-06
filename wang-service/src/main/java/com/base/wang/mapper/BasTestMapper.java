package com.base.wang.mapper;

import com.base.wang.entity.BasTest;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;
@Component
public interface BasTestMapper extends Mapper<BasTest> {

    BasTest findById(Integer id);
}