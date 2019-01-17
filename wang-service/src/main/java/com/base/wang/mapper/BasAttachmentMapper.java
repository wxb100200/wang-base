package com.base.wang.mapper;

import com.base.wang.entity.BasAttachment;
import tk.mybatis.mapper.common.Mapper;

public interface BasAttachmentMapper extends Mapper<BasAttachment> {
    /**
     * 根据文件名称查找附件
     */
    BasAttachment findByFileName(String fileName);
}