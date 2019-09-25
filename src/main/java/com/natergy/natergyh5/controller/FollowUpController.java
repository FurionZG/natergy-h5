package com.natergy.natergyh5.controller;


import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.FollowUp;
import com.natergy.natergyh5.entity.ResultOfAddress;
import com.natergy.natergyh5.entity.WXJsSdk;
import com.natergy.natergyh5.service.FollowUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/followUp")
public class FollowUpController {

    @Autowired
    private FollowUpService followUpService;

    /**
     * 初始化地产跟进的控制器
     * @param request
     * @return
     */
    @RequestMapping("/init")
    public ModelAndView followUpInit(HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        String user =  (String)request.getSession().getAttribute("user");
        List<FollowUp> resultList = followUpService.getFollowUpByUser(user);
        request.setAttribute("followUpList", JSON.toJSONString(resultList));
        mv.setViewName("forward:/jsp/followUp/followUp.jsp");
        return mv;
    }

    @RequestMapping("/followUpAddInit")
    public ModelAndView followUpAddInit (HttpServletRequest request){
        ModelAndView mv = new ModelAndView();
        String uname = (String) request.getSession().getAttribute("user");
        List<String> allCompanysNameList = followUpService.getAllCompanys(uname);

        WXJsSdk d= new WXJsSdk();
        Map map1= d.getAccessToken("wxf5304d7eb35a3907","9c7316b65bb71046c80d4532709d4b56");
        Map map2=d.getJsapiTicket((String) map1.get("accessToken"));
        String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
        String noncestr = UUID.randomUUID().toString().replace("-", "");
        String url="http://www.iluluya.com/natergy-h5/FollowUpAddInit";
        String str = "jsapi_ticket=" + map2.get("ticket") + "&noncestr=" + noncestr + "&timestamp=" + timestamp + "&url=" + url;
        String signature = d.SHA1(str);

        request.setAttribute("allCompanysNameList", JSON.toJSONString(allCompanysNameList));
        request.setAttribute("signature", signature);
        request.setAttribute("timestamp", timestamp);
        request.setAttribute("noncestr", noncestr);
        request.setAttribute("appId", "wxf5304d7eb35a3907");
        mv.setViewName("forward:/jsp/followUp/followUpAdd.jsp");
        return mv;
    }

    @RequestMapping("/getAddress")
    public void getAddress(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String companyName = request.getParameter("name");
        String uname = (String) request.getSession().getAttribute("user");
        ResultOfAddress customerInfo = followUpService.getAddressInfo(companyName,uname);
        response.getWriter().write(JSON.toJSONString(customerInfo));
    }

    @RequestMapping("/save")
    public void saveFollowUp(@RequestBody FollowUp followUp,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String uname = (String) request.getSession().getAttribute("user");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        followUp.setUser(uname);
        followUp.setDate(sdf.format(new Date()));
        Integer reten= followUpService.saveFollowUp(followUp);
        response.getWriter().write(JSON.toJSONString(reten));
    }
}
