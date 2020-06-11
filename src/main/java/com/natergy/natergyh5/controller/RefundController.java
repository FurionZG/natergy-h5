package com.natergy.natergyh5.controller;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.Order;
import com.natergy.natergyh5.entity.OrderDetail;
import com.natergy.natergyh5.entity.Refund;
import com.natergy.natergyh5.service.OrderService;
import com.natergy.natergyh5.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *  退货控制器
 * @author 杨枕戈
 */
@RestController
@RequestMapping("/refund")
public class RefundController {

    @Autowired
    private RefundService refundService;

    /**
     * 初始化退货
     * @return 将当前用户的最后10条退货列表和有退货的客户名称列表添加到模型中，并转发到refund.jsp
     */
    @RequestMapping("/init")
    public ModelAndView refundInit(HttpSession session){
        String uname = (String) session.getAttribute("user");
        ModelAndView mv = new ModelAndView("forward:/jsp/refund/refund.jsp");
        List<Refund> resultList = refundService.queryRefunds(uname);
        Set<String> customerNameList = refundService.queryCustomerName(uname);
        mv.addObject("refundInfoListByUser", JSON.toJSONString(resultList));
        mv.addObject("refundNameSet", JSON.toJSONString(customerNameList));
        return mv;
    }

    @RequestMapping("/getRefundInfoByAjax")
    public List<Refund> getRefundInfoByAjax(@RequestParam String customerName, HttpSession session) {
        String uname = (String) session.getAttribute("user");
        return refundService.getOrdersInfoByAjax(customerName, uname);
    }


    @RequestMapping("/refresh")
    public List<Refund> refundRefresh(HttpSession session)  {
        String uname = (String) session.getAttribute("user");
        return refundService.queryRefunds(uname);
    }



    @RequestMapping("/reload")
    public List<Refund> refundReload(Integer limit, HttpSession session)  {
        String uname = (String) session.getAttribute("user");
        return refundService.reloadRefundsLimit(uname, limit);
    }

    @RequestMapping("/getRefundInfoBySalesman")
    public List<Refund> getRefundInfoBySalesman(String salesmanName){
        return refundService.getRefundInfoBySalesman(salesmanName);
    }


    @RequestMapping("/addDetailInit")
    public ModelAndView refundDetailAddInit()  {
        ModelAndView  modelAndView = new ModelAndView("forward:/jsp/refund/refundDetailsAdd.jsp");
        List<Map<String, Object>> result =  refundService.getAllProductInfo();
        modelAndView.addObject("result",JSON.toJSONString(result));
        return modelAndView;
    }

    /**
     * 保存退货
     * @param refund 前台提交的退货对象
     * @return 返回插入返回值的乘积
     */
    @RequestMapping("/save")
    @ResponseBody
    public Integer refundSave(@RequestBody Refund refund, HttpSession session)  {
        String uname = (String) session.getAttribute("user");
        return refundService.save(refund,uname);
    }
}
