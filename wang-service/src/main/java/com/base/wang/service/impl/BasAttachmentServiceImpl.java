package com.base.wang.service.impl;

import com.base.wang.common.PageReturn;
import com.base.wang.entity.BasAttachment;
import com.base.wang.mapper.BasAttachmentMapper;
import com.base.wang.service.BasAttachmentService;
import com.base.wang.util.DateUtil;
import com.base.wang.util.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;

/**
 * Created by wxb on 2018/12/28.
 */
@Service("basAttachmentService")
public class BasAttachmentServiceImpl extends BaseServiceImpl<BasAttachment> implements BasAttachmentService {
    @Autowired
    BasAttachmentMapper attachmentMapper;
    /**
     * 根据文件名称查找附件
     */
    @Override
    public BasAttachment findByFileName(String fileName) {
        return attachmentMapper.findByFileName(fileName) ;
    }
}
