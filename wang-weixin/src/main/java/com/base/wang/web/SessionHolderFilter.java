package com.base.wang.web;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by wxb on 2018/9/28.
 */
public class SessionHolderFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
//        System.out.println("url:"+req.getRequestURI().toString());

        SessionHolder.init(req);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
