package com.natergy.natergyh5.controller;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.Offer;
import com.natergy.natergyh5.service.CustomerService;
import com.natergy.natergyh5.service.PriceListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

/**
 * @Author: 杨枕戈
 * @Date: 2019/12/6 10:02
 */

@RestController
@RequestMapping("/excelToImage")
public class PriceListController {


    @Autowired
    private PriceListService priceListService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/get")
    @ResponseBody
    public String getImage(@RequestBody Offer offer, HttpSession session) {
        offer.setDate(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
        offer.setUser((String) session.getAttribute("user"));
        offer.setStatus("草稿");
        if (this.priceListService.excelToPng(offer).booleanValue()) {
            return JSON.toJSONString("ok");
        }
        return JSON.toJSONString("error");
    }

    @RequestMapping("/init")
    public ModelAndView queryCustomerName(HttpSession session) {
        String uname = (String) session.getAttribute("user");
        ModelAndView mv = new ModelAndView("forward:/jsp/priceList/priceList.jsp");
        Set<String> customerNameList = this.customerService.queryCustomerName(uname);
        mv.addObject("nameSet", JSON.toJSONString(customerNameList));
        return mv;
    }


}
