package com.natergy.natergyh5.controller;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.Order;
import com.natergy.natergyh5.entity.OrderDetail;
import com.natergy.natergyh5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.tags.Param;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单相关Controller
 */
@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 初始化订单页面控制器
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

    /**
     * 初始化订单添加页控制器
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/addInit")
    public void orderAddInit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        ModelAndView mv = new ModelAndView();
        List<String> customerList=orderService.queryCustomersByUser(uname);
        response.getWriter().write(JSON.toJSONString(customerList));
    }
    /**
     * 初始化添加订单明细控制器
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/addDetailInit")
    public void orderDetailAddInit(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        List<Map<String, Object>> result=orderService.getAllProductInfo();
        request.getSession().setAttribute("result", JSON.toJSONString(result));
        request.getRequestDispatcher("/jsp/order/orderDetailAdd.jsp").forward(request, response);
    }

    /**
     * 保存订单控制器
     * @param order
     * @param request
     */
    @RequestMapping("/save")
    @ResponseBody
    public void orderSave(@RequestBody Order order, HttpServletRequest request,HttpServletResponse response) throws IOException {
        Date nowDateStamp = new Date();
        String orderNumber = ("D" + new SimpleDateFormat("yyyyMMddHHmmss").format(nowDateStamp)).substring(0, 15);
        String orderTime = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(nowDateStamp);
        order.setOrderNumber(orderNumber);
        order.setOrderTime(orderTime);
        for(OrderDetail od : order.getOrderDetails()){
            od.setTotalWeight(String.valueOf(od.getOutwrapper().contains("桶")?160*Integer.parseInt(od.getCount()):25*Integer.parseInt(od.getCount())));
            od.setTotalPrice(String.valueOf(Double.parseDouble(od.getTotalWeight())*Double.parseDouble(od.getPrice())));
            od.setOrderNumber(order.getOrderNumber());
        }
        String uname = (String) request.getSession().getAttribute("user");
        Integer reten =orderService.saveOrderTranscation(order,uname);
        response.getWriter().write(JSON.toJSONString(reten));
    }

    /**
     * 下拉刷新控制器
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/refresh")
    public void orderRefresh(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        List<Order> resultList=orderService.queryOrders(uname);
        response.getWriter().write(JSON.toJSONString(resultList));
    }

    @RequestMapping("/reload")
    public void orderReload(Integer limit,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        List<Order> resultList=orderService.reloadOrdersLimit(uname,limit);
        response.getWriter().write(JSON.toJSONString(resultList));
    }


}
