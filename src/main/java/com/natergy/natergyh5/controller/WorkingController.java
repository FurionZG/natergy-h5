package com.natergy.natergyh5.controller;


import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.WXJsSdk;
import com.natergy.natergyh5.entity.Working;
import com.natergy.natergyh5.entity.WxToken;
import com.natergy.natergyh5.service.WorkingService;
import com.natergy.natergyh5.utils.WxUtils;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/working")
public class WorkingController {

    @Value("${natergy.appId}")
    private String appId;
    @Value("${natergy.appSecret}")
    private String appSecret;
    @Value("${natergy.host}")
    private String host;
    @Autowired
    private WorkingService workingService;
    @Autowired
    private WxUtils wxUtils;
    @Value("${SalesExecutive}")
    String salesExecutive;

    @RequestMapping("/init")
    public ModelAndView workingInit(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String uname = (String) request.getSession().getAttribute("user");
        List<Working> resultList = workingService.getWorkings(uname);
        Set<String> workingsNameSet = workingService.getWorkingsName(uname);
        request.setAttribute("workings", JSON.toJSONString(resultList));
        request.setAttribute("workingNameSet", JSON.toJSONString(workingsNameSet));
        request.setAttribute("salesExecutive", JSON.toJSONString(salesExecutive));
        mv.setViewName("forward:/jsp/working/working.jsp");
        return mv;
    }

    @RequestMapping("/workingAddInit")
    public ModelAndView workingAddInit(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mv = new ModelAndView();
        WxToken wxToken = wxUtils.getWxToken("/natergy-h5/working/workingAddInit");
        request.setAttribute("signature", wxToken.getSignature());
        request.setAttribute("timestamp", wxToken.getTimestamp());
        request.setAttribute("noncestr", wxToken.getNoncestr());
        request.setAttribute("appId", wxToken.getAppId());
        mv.setViewName("forward:/jsp/working/workingAdd.jsp");
        return mv;
    }

    @RequestMapping("/save")
    public void workingSave(@RequestBody Working working, HttpServletRequest request, HttpServletResponse response) throws Exception {
        working.setUser((String) request.getSession().getAttribute("user"));
        Integer reten = workingService.saveWorking(working);
        response.getWriter().write(JSON.toJSONString(reten));
    }

    @RequestMapping("/update")
    public void workingUpdate(@RequestBody Working working, HttpServletResponse response) throws Exception {
        Integer reten = workingService.updateWorking(working);
        response.getWriter().write(JSON.toJSONString(reten));
    }

    @RequestMapping("/workingEditInit")
    public ModelAndView workingEditInit(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        WxToken wxToken = wxUtils.getWxToken("/natergy-h5/working/workingEditInit");
        request.setAttribute("signature", wxToken.getSignature());
        request.setAttribute("timestamp", wxToken.getTimestamp());
        request.setAttribute("noncestr", wxToken.getNoncestr());
        request.setAttribute("appId", wxToken.getAppId());
        mv.setViewName("forward:/jsp/working/workingEdit.jsp");
        return mv;
    }

    @RequestMapping("/refresh")
    public void workingRefresh(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        List<Working> resultList = workingService.getWorkings(uname);
        response.getWriter().write(JSON.toJSONString(resultList));
    }

    @RequestMapping("/reload")
    public void workingReload(String name, Integer limit, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        List<Working> resultList = new ArrayList<>();
        if (name.equals("")) {
            resultList = workingService.reloadWorkings(limit, uname);
        }else{
            resultList = workingService.reloadWorkingsByName(limit, uname,name);
        }
        response.getWriter().write(JSON.toJSONString(resultList));
    }

    @RequestMapping("/getWorkingsByName")
    public void getWorkingsByName(String name, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        List<Working> resultList = workingService.getWorkingsByName(uname, name);
        response.getWriter().write(JSON.toJSONString(resultList));
    }
}
