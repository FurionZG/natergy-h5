package com.natergy.natergyh5.controller;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.Customer;
import com.natergy.natergyh5.entity.ResultOfSelectCustomerInfoByName;
import com.natergy.natergyh5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("/save")
    @ResponseBody
    public void saveCustomer(@RequestBody Customer customer, HttpServletResponse response, HttpServletRequest request) throws IOException {

        String uname = (String) request.getSession().getAttribute("user");
        Integer reten = orderService.saveCustomer(customer,uname);
        response.getWriter().write(JSON.toJSONString(reten));
    }
}
