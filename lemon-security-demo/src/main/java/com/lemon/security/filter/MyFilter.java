package com.lemon.security.filter;


import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
@Component
public class MyFilter implements Filter {
    private  int i=1;
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("MyFilter  init---------");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("MyFilter do  执行了-----------"+i);
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {
        System.out.println("MyFilter 销毁了。。。。");

    }
}
