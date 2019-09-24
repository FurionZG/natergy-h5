package com.natergy.natergyh5.controller;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.ResultOfSelectCustomerInfoByName;
import com.natergy.natergyh5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private OrderService orderService;
    @RequestMapping("/getCustomerInfo")
    public void getCustomerInfo(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String customerName = request.getParameter("name");
        ResultOfSelectCustomerInfoByName customerInfo = orderService.getCustomerInfoByName(customerName);
        response.getWriter().write(JSON.toJSONString(customerInfo));
    }
}
