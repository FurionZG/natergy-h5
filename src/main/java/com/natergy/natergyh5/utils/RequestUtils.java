package com.natergy.natergyh5.utils;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 请求工具类
 * @author 杨枕戈
 */
public class RequestUtils {

    public static HttpServletRequest getRequest() {
        ServletRequestAttributes ra= (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return ra.getRequest();
    }

}
