package com.base.wang.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by wxb on 2018/9/28.
 */
public class SessionFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;
        Integer userId= SessionHolder.getCurrentUserId();
        String path=request.getServletPath();

        //不需要登陆的
        String[] noNeedLoginPaths={"/static","/web/login","/web/register","/user/login","/user/register"};
        if(isMatch(path,noNeedLoginPaths)){
            chain.doFilter(req,res);
        }else{
            if(userId==null){
                response.sendRedirect("/web/login");
            }else{
                chain.doFilter(req,res);
            }
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isMatch(String path, String[] paths){
        boolean isMatch = false;
        for(String p:paths){
            if(path.startsWith(p)){
                isMatch = true;
                break;
            }
        }
        return isMatch;
    }
}
