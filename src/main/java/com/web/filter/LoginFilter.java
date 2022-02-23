package com.web.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest)request;
        //判断访问资源路径是否与登录注册相关（跳转到登录界面不受限制）
        String[] urls = {"/imgs/","/css/","/register.jsp","/loginServlet","/register.jsp","/registerServlet","/checkCodeServlet","/usernameServlet"};
        //获取当前访问路径
        String url = req.getRequestURL().toString();

        //循环判断
        for(String u : urls)
        {
            if(url.contains(u))
            {
                filterChain.doFilter(req,response);
                return;
            }
        }

        //判断session是否存在user
        HttpSession session = req.getSession();
        Object user = session.getAttribute("user");
        if(user != null)
        {
            //已登录
            filterChain.doFilter(request,response);
        }
        else
        {
            //未登录
            req.setAttribute("login_Msg","您尚未登录!");
            req.getRequestDispatcher("/login.jsp").forward(req,response);
        }


    }

    @Override
    public void destroy() {

    }
}
