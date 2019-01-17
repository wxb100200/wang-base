package com.base.wang.convert;

/**
 * Created by wxb on 2018/12/13.
 */
public class PasswordConvert extends DefaultConvert {
    @Override
    public Object convert(Object val){
        if(val==null) return "";
        return "*********";
    }
}
