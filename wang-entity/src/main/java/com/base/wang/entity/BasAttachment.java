package com.base.wang.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bas_attachment")
public class BasAttachment {
    @Id
    private Integer id;

    /**
     * 用户上传的文件名称
     */
    private String name;

    /**
     * 文件名称
     */
    @Column(name = "file_name")
    private String fileName;

    /**
     * 存放文件路径
     */
    @Column(name = "file_path")
    private String filePath;

    /**
     * 文件大小
     */
    @Column(name = "file_size")
    private Long fileSize;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private Date createTime;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取用户上传的文件名称
     *
     * @return name - 用户上传的文件名称
     */
    public String getName() {
        return name;
    }

    /**
     * 设置用户上传的文件名称
     *
     * @param name 用户上传的文件名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取文件名称
     *
     * @return file_name - 文件名称
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * 设置文件名称
     *
     * @param fileName 文件名称
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 获取存放文件路径
     *
     * @return file_path - 存放文件路径
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * 设置存放文件路径
     *
     * @param filePath 存放文件路径
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    /**
     * 获取文件大小
     *
     * @return file_size - 文件大小
     */
    public Long getFileSize() {
        return fileSize;
    }

    /**
     * 设置文件大小
     *
     * @param fileSize 文件大小
     */
    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}