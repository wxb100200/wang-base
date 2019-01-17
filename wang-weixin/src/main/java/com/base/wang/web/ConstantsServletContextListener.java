package com.base.wang.web;



import com.base.wang.common.Global;
import com.base.wang.entity.BasConfigParam;
import com.base.wang.service.ConfigParamService;
import com.base.wang.util.WXAccessTokenUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.List;

/**
 * 监听
 */
public class ConstantsServletContextListener implements ServletContextListener {

    /**
     *当服务启动时，读取BasConfigParam中的所有数据，存入全局常量中
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context=servletContextEvent.getServletContext();
        ApplicationContext ctx= WebApplicationContextUtils.getRequiredWebApplicationContext(context);
        ConfigParamService configParamService=(ConfigParamService)ctx.getBean("configParamService");
        List<BasConfigParam> list = configParamService.findAllConfig();
        for(int i=0;i<list.size();i++){
            BasConfigParam param=list.get(i);
            Global.CONFIG.put(param.getCode(),param.getValue());
            System.out.println("------->>>>>>>>>>>>>>"+param.getCode()+":::"+param.getValue());
        }
        String accessToken= WXAccessTokenUtil.getAccessToken();
        Global.CONFIG.put("accessToken",accessToken);
        System.out.println("------->>>>>>>>>>>>>>accessToken::::"+accessToken);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
