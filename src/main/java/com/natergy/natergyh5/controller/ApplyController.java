package com.natergy.natergyh5.controller;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.Apply;
import com.natergy.natergyh5.entity.Visit;
import com.natergy.natergyh5.service.ApplyService;
import com.natergy.natergyh5.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: 杨枕戈
 * @Date: 2019/11/27 13:19
 */

@RestController
@RequestMapping("/apply")
public class ApplyController {

    @Autowired
    private ApplyService applyService;
    @Autowired
    private CustomerService customerService;

    @RequestMapping("/init")
    public ModelAndView applyInit(HttpSession session){
        ModelAndView mv = new ModelAndView("forward:/jsp/apply/apply.jsp");
        String uname =  (String)session.getAttribute("user");
        List<Apply> resultList = applyService.getApplysByUser(uname);
        List<String> customerNameList = applyService.getCustomerNames(uname);
        mv.addObject("applyList", JSON.toJSONString(resultList));
        mv.addObject("ApplysNameSet", JSON.toJSONString(customerNameList));
        return mv;
    }

    @RequestMapping("/refresh")
    public List<Apply> applyRefresh(HttpSession session) {
        String uname = (String) session.getAttribute("user");
        return applyService.refreshApply(uname);
    }

    @RequestMapping("/reload")
    public List<Apply> applyReload(Integer limit,HttpSession session) {
        String uname = (String) session.getAttribute("user");
        return applyService.reloadApply(uname,limit);
    }

    @RequestMapping("/addInit")
    public ModelAndView applyAddInit(HttpSession session){
        ModelAndView mv = new ModelAndView("forward:/jsp/apply/applyAdd.jsp");
        String uname =  (String)session.getAttribute("user");
        List<String> resultList = customerService.getCustomerNameList(uname);
        mv.addObject("allCompanysNameList", JSON.toJSONString(resultList));
        return mv;
    }

    @RequestMapping("/save")
    @ResponseBody
    public Integer applySave(@RequestBody Apply apply, HttpSession session){
        String uname = (String)session.getAttribute("user");
        return applyService.saveApply(apply,uname);
    }

    @RequestMapping("/update")
    @ResponseBody
    public Integer applyUpdate(@RequestBody Apply apply, HttpSession session){
        String uname = (String)session.getAttribute("user");
        return applyService.updateApply(apply,uname);
    }

    @RequestMapping("/getApplyByAjax")
    public List<Apply> getApplyByAjax(String customerName, HttpSession session){
        String uname = (String)session.getAttribute("user");
        return applyService.getApplyByAjax(customerName,uname);
    }

    @RequestMapping("/approval")
    @ResponseBody
    public Integer saveApproval(@RequestBody Apply apply){
        return applyService.saveApproval(apply);
    }

}
