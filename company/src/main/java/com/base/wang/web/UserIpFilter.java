package com.base.wang.web;



import com.base.wang.util.IpUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * 用户ip过滤器
 */
public class UserIpFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request=(HttpServletRequest)req;
        IpUtil.set(request);
        chain.doFilter(req, res);
        IpUtil.clear();
    }

    @Override
    public void destroy() {

    }
}
