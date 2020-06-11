package com.natergy.natergyh5.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 登录过滤器
 * @author 杨枕戈
 */
@WebFilter(filterName = "LoginFilter",urlPatterns = "/*")
public class LoginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        if (req.getServletPath().contains("/login.jsp") || req.getServletPath().contains("/login") || req.getServletPath().contains(".txt")
                || req.getServletPath().endsWith(".js") || req.getServletPath().endsWith(".css")||req.getServletPath().contains("/wxOpenId")||req.getServletPath().contains("bind")||req.getServletPath().contains("notice/viewInit")||req.getServletPath().contains("/infactory/dacang")) {
            chain.doFilter(request, response);
            return;
        }

        HttpSession session = req.getSession();
        Object oUser = session.getAttribute("user");
        if (null==oUser) {// 用户为空不拦截
            if(req.getRequestURL().toString().contains("infactory")){

                resp.setHeader("content-type", "text/html;charset=UTF-8");
                resp.getWriter().write("<script>alert('请您登录！');window.location.href='"+req.getContextPath()+"/jsp/infactory/login.jsp';</script>");
                return;
            }else{

                resp.setHeader("content-type", "text/html;charset=UTF-8");
                resp.getWriter().write("<script>alert('请您登录！');window.location.href='"+req.getContextPath()+"/login.jsp';</script>");
                return;
            }

        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
