package com.natergy.natergyh5.aop;


import com.natergy.natergyh5.annotations.OperationLog;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 
 * @Author 杨枕戈
 * @Date 2020-03-18 16:15
 * @Version 1.0
 * 
 */
@Aspect
@Component
public class OperationLogApsect {
    private static Logger logger = LogManager.getLogger(OperationLogApsect.class);


    @Pointcut("@annotation(com.natergy.natergyh5.annotations.OperationLog)")
    public void operationLog() {
    }

    @Before("operationLog()&&@annotation(log)")
    public void before(JoinPoint joinPoint, OperationLog log) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        /**
         * 接口调用信息可以记日志，也可以写到数据库
         */
        StringBuilder sb = new StringBuilder();
        sb.append("\n时间：").append(new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date()));
        sb.append("\n模块：").append(log.module());
        sb.append("\n操作：").append(log.operate());
        sb.append("\n操作人：").append(request.getSession().getAttribute("user"));
        sb.append("\n接口名称：").append(joinPoint.getSignature().getName());
        sb.append("\nIP：").append(request.getRemoteHost());
        Object[] os  = joinPoint.getArgs();
        for(Object o :os){
            sb.append("\n传入对象：").append(o.toString());
        }
        logger.info(sb.toString());
    }



}
