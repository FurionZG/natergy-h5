package com.natergy.natergyh5.controller.infactory;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.infactory.Cook;
import com.natergy.natergyh5.service.infactory.CookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 
 * @Author 杨枕戈
 * @Date 2020-02-23 11:18
 * @Version 1.0
 * 
 */

@RestController
@RequestMapping("/infactory/cook")
public class CookController {
    @Autowired
    private CookService cookService;

    @RequestMapping("/init")
    public ModelAndView init(HttpSession session){
        String uname = (String) session.getAttribute("user");
        ModelAndView mv = new ModelAndView("forward:/jsp/infactory/cook/cook.jsp");
        String today = new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
        Cook yesterdayCook = cookService.queryYesterdayCook(uname);
        mv.addObject("today", JSON.toJSONString(today));
        mv.addObject("yesterdayCook",JSON.toJSONString (yesterdayCook));
        return mv;
    }

    @RequestMapping("/add")
    @ResponseBody
    public String addCook(@RequestBody Cook cook, HttpSession session){
        String today =new SimpleDateFormat("yyyy年MM月dd日").format(new Date());
        String uname = (String) session.getAttribute("user");
        cook.setDate(today);
        if("".equals(cook.getToCook())){
            cook.setUser(uname);
        }else{
            cook.setUser(cook.getToCook());
            cook.setRemarks(cook.getRemarks()+"    订餐人:"+uname);
        }
        //TODO 这里要对时间进行判断
        cook.setQ("1111111111111111111111111111111011111100000000000000000000000000000000000000000000000000000000000000000");
        Integer reten  = cookService.addCook(cook);
        return JSON.toJSONString(reten);
    }

    @RequestMapping("/listInit")
    public ModelAndView listInit(HttpSession session){
        String uname = (String) session.getAttribute("user");
        List<Cook> cookList = cookService.queryCookList(uname);
        ModelAndView mv = new ModelAndView("forward:/jsp/infactory/cook/cookList.jsp");
        mv.addObject("cookList",JSON.toJSONString (cookList));
        return mv;
    }

    @RequestMapping("/listRefresh")
    public String listRefresh(HttpSession session){
        String uname = (String)session.getAttribute("user");
        List<Cook> cookList = cookService.queryCookList(uname);
        return cookList.toString();
    }

    @RequestMapping("/listReload")
    public String listReload(Integer limit,HttpSession session){
        String uname = (String)session.getAttribute("user");
        List<Cook> cookList = cookService.queryCookListLimit(uname,limit);
        return JSON.toJSONString(cookList);
    }

    @RequestMapping("/update")
    public String updateCook(@RequestBody Cook cook,HttpSession session){
        String uname = (String)session.getAttribute("user");
        Integer reten =  cookService.updateCook(cook);
        return JSON.toJSONString(reten);
    }

    @RequestMapping("/count")
    public String countCook(String date,String workspace,String eat){
        String resultString = cookService.countCook(date,workspace,eat);
        return JSON.toJSONString(resultString);
    }
}
