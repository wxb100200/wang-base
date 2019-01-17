package com.base.wang.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "bas_account")
public class BasAccount {
    @Id
    private Integer id;

    /**
     * 用户id
     */
    @Column(name = "user_id")
    private Integer userId;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    private String password;

    /**
     * 盐
     */
    private String salt;

    /**
     * 账号类型
     */
    private Integer type;

    /**
     * 账号状态：0、关闭，1、启用
     */
    private Integer status;

    /**
     * 最后登陆时间
     */
    @Column(name = "last_login_time")
    private Date lastLoginTime;

    /**
     * 连续登陆失败次数
     */
    @Column(name = "fail_num")
    private String failNum;

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
     * 获取用户id
     *
     * @return user_id - 用户id
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置用户id
     *
     * @param userId 用户id
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取盐
     *
     * @return salt - 盐
     */
    public String getSalt() {
        return salt;
    }

    /**
     * 设置盐
     *
     * @param salt 盐
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     * 获取账号类型
     *
     * @return type - 账号类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 设置账号类型
     *
     * @param type 账号类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 获取账号状态：0、关闭，1、启用
     *
     * @return status - 账号状态：0、关闭，1、启用
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置账号状态：0、关闭，1、启用
     *
     * @param status 账号状态：0、关闭，1、启用
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取最后登陆时间
     *
     * @return last_login_time - 最后登陆时间
     */
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    /**
     * 设置最后登陆时间
     *
     * @param lastLoginTime 最后登陆时间
     */
    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    /**
     * 获取连续登陆失败次数
     *
     * @return fail_num - 连续登陆失败次数
     */
    public String getFailNum() {
        return failNum;
    }

    /**
     * 设置连续登陆失败次数
     *
     * @param failNum 连续登陆失败次数
     */
    public void setFailNum(String failNum) {
        this.failNum = failNum;
    }
}