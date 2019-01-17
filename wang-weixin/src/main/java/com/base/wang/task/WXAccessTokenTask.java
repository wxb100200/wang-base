package com.base.wang.task;

import com.base.wang.common.Global;
import com.base.wang.util.WXAccessTokenUtil;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 定时获取微信的accessToken
 */
@Component
public class WXAccessTokenTask {


    /**
     * 每小时获取一次微信的accessToken
     */
    @Scheduled(cron = "0 0 0/1 * * ? ")
    public void getAccessToken() {
        String accessToken= WXAccessTokenUtil.getAccessToken();
        Global.CONFIG.put("accessToken",accessToken);
    }
}
