package com.base.wang.util;

import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取微信的accessToken
 */
public class WXAccessTokenUtil {

    private Logger log = Logger.getLogger(this.getClass());

    public static String getAccessToken() {
//        https请求方式: GET
//        https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET
        String url="https://api.weixin.qq.com/cgi-bin/token";
        Map<String,String> params=new HashMap<>();
        params.put("grant_type","client_credential");
        params.put("appid","wxffe21420cd42ee71");
        params.put("secret","362cd76eedf575c84b8ab8cc894781f3");
        String result=HttpUtil.URLGet(url,params,"UTF-8");
        System.out.println("--->>>>>WXAccessTokenTask getAccessToken result:"+result);
        Map map= JsonUtil.json2Map(result);
        if(!map.containsKey("access_token"))return null;
        String accessToken=map.get("access_token").toString();
        System.out.println("--->>>>>定时获取微信的accessToken成功");
        return accessToken;
    }
}
