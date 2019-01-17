package com.base.wang.common;

/**
 * 异常类
 */
public class MyException extends RuntimeException {
    /**
     * 异常编码
     */
    private String code;
    /**
     * 异常内容
     */
    private String messageText;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public MyException(String messageText) {
        this.code="500";
        this.messageText=messageText;
    }
    public MyException(String code,String messageText) {
        this.code=code;
        this.messageText=messageText;
    }
}
