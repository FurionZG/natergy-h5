package com.natergy.natergyh5.controller;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.BusinessApply;
import com.natergy.natergyh5.service.BusinessApplyService;
import com.natergy.natergyh5.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.*;

/**
 * 
 * @Author 杨枕戈
 * @Date 2020-03-28 9:19
 * @Version 1.0
 * 
 */
@RestController
@RequestMapping("/businessApply")
public class BusinessApplyController {

    @Autowired
    private BusinessApplyService businessApplyService;
    @Autowired
    private BusinessService businessService;

    @RequestMapping("/init")
    public ModelAndView businessApplyInit(HttpSession session) {
        ModelAndView mv = new ModelAndView("forward:/jsp/businessApply/businessApply.jsp");
        String uname = (String)session.getAttribute("user");
        List<BusinessApply> businessApplyList = businessApplyService.getBusinessApplyList(uname);
        mv.addObject("businessApplyList", JSON.toJSONString(businessApplyList));
        return mv;
    }

    @RequestMapping("/addInit")
    public ModelAndView businessApplyAddInit(HttpSession session) {
        ModelAndView mv = new ModelAndView("forward:/jsp/businessApply/businessApplyAdd.jsp");
        String uname = (String)session.getAttribute("user");
        return addBusinessNoList(mv, uname);
    }

    @RequestMapping("/save")
    @ResponseBody
    public Integer applySave(@RequestBody BusinessApply businessApply, HttpSession session) throws ParseException {
        String uname = (String)session.getAttribute("user");
        return  businessApplyService.save(businessApply,uname);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Integer applyUpdate(@RequestBody BusinessApply businessApply, HttpSession session) throws ParseException {
        String uname = (String)session.getAttribute("user");
        return  businessApplyService.update(businessApply);
    }

    @RequestMapping("/reload")
    public List<BusinessApply> businessApplyReload(Integer limit, HttpSession session){
        String uname = (String) session.getAttribute("user");
        return businessApplyService.reloadBusinessApply(limit, uname);
    }

    @RequestMapping("/refresh")
    public List<BusinessApply> businessApplyRefresh(HttpSession session){
        String uname = (String) session.getAttribute("user");
        return businessApplyService.refreshBusinessApply(uname);
    }

    @RequestMapping("/editInit")
    public ModelAndView businessApplyEditInit(HttpSession session){
        ModelAndView mv = new ModelAndView("forward:/jsp/businessApply/businessApplyEdit.jsp");
        String uname = (String) session.getAttribute("user");
        return addBusinessNoList(mv, uname);
    }

    private ModelAndView addBusinessNoList(ModelAndView mv, String uname) {
        List<String> businessNoList = businessService.getBusinessNoByUser(uname);
        List<Map<String,String>> resultList = new ArrayList<>();
        for(String tmp :businessNoList){
            Map tmpMap = new HashMap();
            tmpMap.put("text",tmp);
            resultList.add(tmpMap);
        }
        mv.addObject("businessNoList", JSON.toJSONString(resultList));
        return mv;
    }
}
