package com.natergy.natergyh5.controller;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.annotations.NoRepeatSubmit;
import com.natergy.natergyh5.entity.ResultOfAddress;
import com.natergy.natergyh5.entity.Visit;
import com.natergy.natergyh5.entity.WXJsSdk;
import com.natergy.natergyh5.service.BusinessService;
import com.natergy.natergyh5.service.FollowUpService;
import com.natergy.natergyh5.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/visit")
public class VisitController {
    @Value("${natergy.appId}")
    private String appId;
    @Value("${natergy.appSecret}")
    private String appSecret;
    @Value("${natergy.host}")
    private String host;
    @Autowired
    private VisitService visitService;
    @Autowired
    private BusinessService businessService;
    @Autowired
    private FollowUpService followUpService;


    @RequestMapping("/init")
    public ModelAndView visitInit(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        String uname =  (String)request.getSession().getAttribute("user");
        List<Visit> resultList = visitService.getVisitsByUser(uname);
        Set<String> visitCustomerNameList = visitService.getVisitsCustomerNames(uname);
        request.setAttribute("visitList", JSON.toJSONString(resultList));
        request.setAttribute("visitsNameSet", JSON.toJSONString(visitCustomerNameList));
        mv.setViewName("forward:/jsp/visit/visit.jsp");
        return mv;
    }

    @RequestMapping("/visitAddInit")
    public ModelAndView visitAddInit (HttpServletRequest request,HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        String uname = (String) request.getSession().getAttribute("user");
        List<String> allCompanysNameList = visitService.getAllCompanys(uname);
        String businessStartTime = visitService.getbusinessStartTime(uname);
        String businessNo = visitService.getbusinessNo(uname);
        Integer onBusiness = businessService.getOnBusiness(uname);
        if(0==onBusiness){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<script laguage='javascript'>alert('您还没有出差计划，请前往出差模块登记出差计划');window.location.href='/natergy-h5/jsp/main.jsp';</script>");
            return null;
        }

        WXJsSdk d= new WXJsSdk();
        Map map1= d.getAccessToken(appId,appSecret);
        Map map2=d.getJsapiTicket((String) map1.get("accessToken"));
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String noncestr = UUID.randomUUID().toString().replace("-", "");
        String url="http://"+host+"/natergy-h5/visit/visitAddInit";
        String str = "jsapi_ticket=" + map2.get("ticket") + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
        String signature = d.SHA1(str);

        request.setAttribute("businessStartTime", JSON.toJSONString(businessStartTime));
        request.setAttribute("businessNo", JSON.toJSONString(businessNo));
        request.setAttribute("allCompanysNameList", JSON.toJSONString(allCompanysNameList));

        request.setAttribute("signature", signature);
        request.setAttribute("timestamp", timestamp);
        request.setAttribute("noncestr", noncestr);
        request.setAttribute("appId", appId);
        mv.setViewName("forward:/jsp/visit/visitAdd.jsp");
        return mv;
    }

    //@NoRepeatSubmit
    @RequestMapping("/save")
    public void saveFollowUp(@RequestBody Visit visit, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uname = (String) request.getSession().getAttribute("user");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        visit.setUser(uname);
        visit.setDate(sdf.format(new Date()));
        Integer reten= visitService.saveBusiness(visit);
        response.getWriter().write(JSON.toJSONString(reten));
    }


    @RequestMapping("/update")
    @ResponseBody
    public void updateFollowUp(@RequestBody Visit visit,HttpServletRequest request,HttpServletResponse response) throws IOException, IOException {
        String uname = (String) request.getSession().getAttribute("user");
        Integer reten = visitService.updateVisit(visit,uname);
        response.getWriter().write(JSON.toJSONString(reten));
    }


    @RequestMapping("/refresh")
    public void followUpRefresh(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        List<Visit> resultList = visitService.refreshVisit(uname);
        response.getWriter().write(JSON.toJSONString(resultList));
    }

    @RequestMapping("/reload")
    public void followUpReload(Integer limit,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        List<Visit> resultList = visitService.reloadVisit(uname,limit);
        response.getWriter().write(JSON.toJSONString(resultList));
    }


    @RequestMapping("/selectByBusinessNo")
    public void selectVisitByBusinessNo(@RequestBody String businessNo,HttpServletResponse response) throws IOException {
        List<Visit> resultList =visitService.selectVisitByBusiness(businessNo);
        response.getWriter().write(JSON.toJSONString(resultList));
    }

    @RequestMapping("/getVisitCustomerNameByBusinessNo")
    public void getVisitCustomerNameByBusinessNo(@RequestBody String businessNo,HttpServletResponse response) throws IOException {
        List<String>resultList = visitService.selectVisitCustomerNameByBusiness(businessNo);
        Set<String> resultSet= new HashSet<String>(resultList);
        response.getWriter().write(JSON.toJSONString(resultSet));
    }

    @RequestMapping("/getVisitByAjax")
    public void getVisitByAjax(@RequestParam String customerName, @RequestParam String businessNo, HttpServletResponse response) throws IOException {
        List<Visit> resultList = visitService.getVisitsByAjax(customerName,businessNo);
        response.getWriter().write(JSON.toJSONString(resultList));
    }

    @RequestMapping("/getVisitInfoByAjax")
    public void getVisitInfoByAjax(@RequestParam String customerName, HttpServletResponse response,HttpServletRequest request) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        List<Visit> resultList = visitService.getVisitsInfoByAjax(customerName,uname);
        response.getWriter().write(JSON.toJSONString(resultList));
    }

    @RequestMapping("/getAddress")
    public void getAddress(@RequestParam String customerName,HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        ResultOfAddress customerInfo = followUpService.getAddressInfo(customerName,uname);
        response.getWriter().write(JSON.toJSONString(customerInfo));
    }

    @RequestMapping("/delete")
    public String deleteVisit(@RequestParam String id){
        String reten =visitService.deleteVisit(id);
        return JSON.toJSONString(reten);
    }


}
