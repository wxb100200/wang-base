package com.base.wang.service;

import com.base.wang.entity.BasAttachment;


/**
 * Created by wxb on 2018/12/28.
 */
public interface BasAttachmentService  extends BaseService<BasAttachment> {

    /**
     * 根据文件名称查找附件
     */
    BasAttachment findByFileName(String fileName);
}
