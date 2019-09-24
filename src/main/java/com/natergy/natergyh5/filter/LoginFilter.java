package com.natergy.natergyh5.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "LoginFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        if (req.getServletPath().contains("/login.jsp") || req.getServletPath().contains("/login")
                || req.getServletPath().endsWith(".js") || req.getServletPath().endsWith(".css")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession();
        Object oUser = session.getAttribute("user");
        if (null==oUser) {// 用户为空不拦截
            resp.setHeader("content-type", "text/html;charset=UTF-8");
            resp.getWriter().write("<script>alert('请您登录！');window.location.href='"+req.getContextPath()+"/login.jsp';</script>");
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
