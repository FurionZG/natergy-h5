package com.natergy.natergyh5.controller;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.Order;
import com.natergy.natergyh5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * 订单相关Controller
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 初始化订单页面的方法
     * @param request
     * @return
     */
    @RequestMapping("/init")
    public ModelAndView orderInit(HttpServletRequest request){
        String uname = (String) request.getSession().getAttribute("user");
        ModelAndView mv = new ModelAndView();
        List<Order> resultList=orderService.queryOrders(uname);
        request.setAttribute("orderInfoListByUser", JSON.toJSONString(resultList));
        mv.setViewName("forward:/jsp/order/order.jsp");
        return mv;

    }

    @RequestMapping("/addInit")
    public void orderAddInit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        ModelAndView mv = new ModelAndView();
        List<String> customerList=orderService.queryCustomersByUser(uname);
        response.getWriter().write(JSON.toJSONString(customerList));

    }
}
