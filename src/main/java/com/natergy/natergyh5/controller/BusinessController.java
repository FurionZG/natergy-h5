package com.natergy.natergyh5.controller;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.Business;
import com.natergy.natergyh5.entity.WXJsSdk;
import com.natergy.natergyh5.service.BusinessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/business")
public class BusinessController {

    @Autowired
    private BusinessService businessService;
    @Value("${natergy.appId}")
    private String appId;
    @Value("${natergy.appSecret}")
    private String appSecret;
    @Value("${natergy.host}")
    private String host;

    @RequestMapping("/init")
    public ModelAndView businessInit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView();
        String uname = (String) request.getSession().getAttribute("user");
        Integer onBusinessCount=businessService.getOnBusiness(uname);
        List<Business> businessList=businessService.getBusinessByUser(uname);

        WXJsSdk d= new WXJsSdk();
        Map map1= d.getAccessToken(appId,appSecret);
        Map map2=d.getJsapiTicket((String) map1.get("accessToken"));
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String noncestr = UUID.randomUUID().toString().replace("-", "");
        String url="http://"+host+"/natergy-h5/jsp/business/businessEdit.jsp";
        String str = "jsapi_ticket=" + map2.get("ticket") + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
        String signature = d.SHA1(str);

        request.setAttribute("signature", signature);
        request.setAttribute("timestamp", timestamp);
        request.setAttribute("noncestr", noncestr);
        request.setAttribute("appId", appId);

        request.setAttribute("onBusinessCount", JSON.toJSONString(onBusinessCount));
        request.setAttribute("businessList",JSON.toJSONString(businessList));
        mv.setViewName("forward:/jsp/business/business.jsp");
        return mv;
    }

    @RequestMapping("/begin")
    public void businessBegin(HttpServletResponse response,HttpServletRequest request) throws Exception {
        String uname = (String) request.getSession().getAttribute("user");
        Integer onBusinessCount=businessService.setBusinessBegin(uname);
        response.getWriter().write(JSON.toJSONString(onBusinessCount));
    }

    @RequestMapping("/end")
    public void businessEnd(HttpServletResponse response,HttpServletRequest request) throws Exception {
        String uname = (String) request.getSession().getAttribute("user");
        Integer onBusinessCount=businessService.setBusinessEnd(uname);
        response.getWriter().write(JSON.toJSONString(onBusinessCount));
    }

    @RequestMapping("/update")
    public void businessUpdate(@RequestBody Business business, HttpServletResponse response, HttpServletRequest request) throws Exception {
        String uname = (String) request.getSession().getAttribute("user");
        Integer reten=businessService.updateBusiness(business,uname);
        response.getWriter().write(JSON.toJSONString(reten));
    }

    @RequestMapping("/refresh")
    public void businessRefresh(HttpServletRequest request,HttpServletResponse response) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        List<Business> businessList=businessService.getBusinessByUser(uname);
        response.getWriter().write(JSON.toJSONString(businessList));
    }

    @RequestMapping("/reload")
    public void businessReload(Integer limit,HttpServletResponse response,HttpServletRequest request) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        List<Business> businessList=businessService.getBusinessByLimit(uname,limit);
        response.getWriter().write(JSON.toJSONString(businessList));
    }
}
