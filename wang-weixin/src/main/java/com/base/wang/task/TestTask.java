package com.base.wang.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


/**
 * 测试定时任务
 */
@Component
public class TestTask {
    private Logger log = Logger.getLogger(this.getClass());

    @Scheduled(cron = "0 0/5 * * * ? ")
    public void startTask() {
        log.info("--->>>>>我是定时任务开始了"+System.currentTimeMillis());
    }
}
