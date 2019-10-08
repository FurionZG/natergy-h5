package com.natergy.natergyh5.controller;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.Visit;
import com.natergy.natergyh5.entity.WXJsSdk;
import com.natergy.natergyh5.service.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
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


    @RequestMapping("/init")
    public ModelAndView visitInit(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        String uname =  (String)request.getSession().getAttribute("user");
        List<Visit> resultList = visitService.getVisitsByUser(uname);
        System.out.println(resultList);
        request.setAttribute("visitList", JSON.toJSONString(resultList));
        mv.setViewName("forward:/jsp/visit/visit.jsp");
        return mv;
    }

    @RequestMapping("/visitAddInit")
    public ModelAndView visitAddInit (HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        String uname = (String) request.getSession().getAttribute("user");
        List<String> allCompanysNameList = visitService.getAllCompanys(uname);
        String businessStartTime = visitService.getbusinessStartTime(uname);
        String businessNo = visitService.getbusinessNo(uname);

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

    @RequestMapping("/save")
    public void saveFollowUp(@RequestBody Visit visit, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String uname = (String) request.getSession().getAttribute("user");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        visit.setUser(uname);
        visit.setDate(sdf.format(new Date()));
        System.out.println(visit);
        Integer reten= visitService.saveBusiness(visit);
        response.getWriter().write(JSON.toJSONString(reten));
    }


    @RequestMapping("/update")
    @ResponseBody
    public void updateFollowUp(@RequestBody Visit visit,HttpServletRequest request,HttpServletResponse response) throws IOException, IOException {
        String uname = (String) request.getSession().getAttribute("user");
        System.out.println(visit);
        Integer reten = visitService.updateVisit(visit,uname);
        response.getWriter().write(JSON.toJSONString(reten));
    }
}
