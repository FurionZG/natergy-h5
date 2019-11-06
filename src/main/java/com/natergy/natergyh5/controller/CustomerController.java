package com.natergy.natergyh5.controller;

import com.natergy.natergyh5.entity.Customer;
import com.natergy.natergyh5.entity.ResultOfSelectCustomerInfoByName;
import com.natergy.natergyh5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 客户资料控制器
 * @author 杨枕戈
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private OrderService orderService;

    /**
     * 获取客户信息
     * @param customerName 客户名
     * @return 返回该客户的所有客户信息
     */
    @RequestMapping("/getCustomerInfo")
    public ResultOfSelectCustomerInfoByName getCustomerInfo(@RequestParam("name") String customerName, HttpSession session) {
        String uname = (String) session.getAttribute("user");
        return orderService.getCustomerInfoByName(customerName,uname);
    }

    /**
     * 保存新客户
     * @param customer 客户对象
     * @return 是否保存成功
     */
    @RequestMapping("/save")
    @ResponseBody
    public Integer saveCustomer(@RequestBody Customer customer, HttpSession session) {
        String uname = (String) session.getAttribute("user");
        return orderService.saveCustomer(customer,uname);
    }
}
