package com.base.wang.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 * 异常拦截器
 */
public class ExceptionInterceptor implements HandlerInterceptor {

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        return true;
    }  
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
    }

    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
        if (ex != null) {
            PageReturn pageReturn=new PageReturn();
            if(ex instanceof MyException){
                MyException myException=(MyException) ex;
                pageReturn.setResult(false);
                pageReturn.setMessageCode(myException.getCode());
                pageReturn.setMessageText(myException.getMessageText());
            }else{
                pageReturn.setResult(false);
                pageReturn.setMessageCode("ys-open.sys.error");
                pageReturn.setMessageText("系统错误");
            }
            ObjectMapper mapper=new ObjectMapper();
            String json=mapper.writeValueAsString(pageReturn);
            response.setContentType("application/json;charset=UTF-8");
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            PrintWriter out =response.getWriter();
            out.print(json);
            out.flush();
            out.close();
        }
    }  
      
}  