package com.natergy.natergyh5.controller;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.Order;
import com.natergy.natergyh5.entity.OrderDetail;
import com.natergy.natergyh5.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 订单控制器
 * @author 杨枕戈
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    /**
     * 初始化订单
     * @return 将当前用户的最后10条订单列表和有订单的客户名称列表添加到模型中，并转发到order.jsp
     */
    @RequestMapping("/init")
    public ModelAndView orderInit(HttpSession session) {
        String uname = (String) session.getAttribute("user");
        ModelAndView mv = new ModelAndView("forward:/jsp/order/order.jsp");
        List<Order> resultList = orderService.queryOrders(uname);
        Set<String> customerNameList = orderService.queryCustomerName(uname);
        mv.addObject("orderInfoListByUser", JSON.toJSONString(resultList));
        mv.addObject("orderNameSet", JSON.toJSONString(customerNameList));
        return mv;

    }

    /**
     * 初始化添加订单
     * @return 返回客户经理为当前用户的所有客户
     */
    @RequestMapping("/addInit")
    public List<String> orderAddInit(HttpSession session) {
        String uname = (String)session.getAttribute("user");
        return orderService.queryCustomersByUser(uname);
    }


    /**
     * 初始化添加订单明细
     * @return 设置
     */
    @RequestMapping("/addDetailInit")
    public ModelAndView orderDetailAddInit()  {
        ModelAndView  modelAndView = new ModelAndView("forward:/jsp/order/orderDetailAdd.jsp");
        List<Map<String, Object>> result = orderService.getAllProductInfo();
        modelAndView.addObject("result",JSON.toJSONString(result));
        return modelAndView;


    }

    /**
     * 保存订单
     * @param order 前台提交的订单对象
     * @return 返回插入返回值的乘积
     */
    @RequestMapping("/save")
    @ResponseBody
    public Integer orderSave(@RequestBody Order order, HttpSession session)  {
        Date nowDateStamp = new Date();
        //设置订单号
        String orderNumber = ("D" + new SimpleDateFormat("yyyyMMddHHmmss").format(nowDateStamp)).substring(0, 15);
        //设置订单时间
        String orderTime = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(nowDateStamp);
        order.setOrderNumber(orderNumber);
        order.setOrderTime(orderTime);
        //循环遍历订单明细，设置总重量、总价格和订单明细的订单号
        for (OrderDetail od : order.getOrderDetails()) {
            od.setTotalWeight(String.valueOf(od.getOutwrapper().contains("桶") ? 160 * Integer.parseInt(od.getCount()) : 25 * Integer.parseInt(od.getCount())));
            od.setTotalPrice(String.valueOf(Double.parseDouble(od.getTotalWeight()) * Double.parseDouble(od.getPrice())));
            od.setOrderNumber(order.getOrderNumber());
        }
        String uname = (String) session.getAttribute("user");
        return orderService.saveOrderTranscation(order, uname);
    }

    /**
     * 下拉刷新
     * @return 返回重新查询的订单列表
     */
    @RequestMapping("/refresh")
    public List<Order> orderRefresh(HttpSession session)  {
        String uname = (String) session.getAttribute("user");
        return orderService.queryOrders(uname);
    }


    /**
     * 上拉加载更多
     * @param limit 当前订单条数
     * @return 返回从limit开始后面的5条订单
     */
    @RequestMapping("/reload")
    public List<Order> orderReload(Integer limit, HttpSession session)  {
        String uname = (String) session.getAttribute("user");
        return orderService.reloadOrdersLimit(uname, limit);
    }

    /**
     * 已到货
     * @param orderNumber 订单号
     * @return 返回是否更新成功
     */
    @RequestMapping("/arrived")
    public Integer updateArrived(String orderNumber)  {
        return orderService.updateArrived(orderNumber);
    }

    /**
     * 通过ajax获取某个客户的订单信息
     * @param customerName 客户名称
     * @return 返回该客户的最后10条订单信息
     */
    @RequestMapping("/getOrderInfoByAjax")
    public List<Order> getOrderInfoByAjax(@RequestParam String customerName, HttpSession session) {
        String uname = (String) session.getAttribute("user");
        return orderService.getOrdersInfoByAjax(customerName, uname);
    }

    /**
     * 更新订单信息（只有草稿状态可以编辑订单信息）
     * @param order 被选中订单
     * @return 返回是否更新成功
     */
    @RequestMapping("/update")
    public Integer orderUpdate(@RequestBody Order order, HttpSession session)  {
        String uname = (String) session.getAttribute("user");
        return orderService.updateOrder(order, uname);
    }
}
