package com.natergy.natergyh5.controller;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;


    @RequestMapping("/init")
    public void businessInit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        Integer onBusinessCount=businessService.getOnBusiness(uname);
        response.getWriter().write(JSON.toJSONString(onBusinessCount));
    }

    @RequestMapping("/begin")
    public void businessBegin(HttpServletResponse response,HttpServletRequest request) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        Integer onBusinessCount=businessService.setBusinessBegin(uname);
        response.getWriter().write(JSON.toJSONString(onBusinessCount));
    }

    @RequestMapping("/end")
    public void businessEnd(HttpServletResponse response,HttpServletRequest request) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        Integer onBusinessCount=businessService.setBusinessEnd(uname);
        response.getWriter().write(JSON.toJSONString(onBusinessCount));
    }
}
