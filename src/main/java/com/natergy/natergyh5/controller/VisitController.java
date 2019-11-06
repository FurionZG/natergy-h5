package com.natergy.natergyh5.controller;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.ResultOfAddress;
import com.natergy.natergyh5.entity.Visit;
import com.natergy.natergyh5.entity.WxToken;
import com.natergy.natergyh5.service.BusinessService;
import com.natergy.natergyh5.service.FollowUpService;
import com.natergy.natergyh5.service.VisitService;
import com.natergy.natergyh5.utils.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 销售拜访控制器
 * @author 杨枕戈
 */
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
    @Autowired
    private WxUtils wxUtils;

    /**
     * 销售拜访初始化
     * @return 将当前用户的最近10条销售拜访记录和当前用户拜访过的公司名称列表添加到模型，并转发到visit.jsp
     */
    @RequestMapping("/init")
    public ModelAndView visitInit(HttpSession session){
        ModelAndView mv = new ModelAndView("forward:/jsp/visit/visit.jsp");
        String uname =  (String)session.getAttribute("user");
        List<Visit> resultList = visitService.getVisitsByUser(uname);
        Set<String> visitCustomerNameList = visitService.getVisitsCustomerNames(uname);
        mv.addObject("visitList", JSON.toJSONString(resultList));
        mv.addObject("visitsNameSet", JSON.toJSONString(visitCustomerNameList));
        return mv;
    }

    /**
     * 初始化添加销售拜访，如果现在不在出差状态，则不允许添加拜访记录
     * @return 将当前出差的开始时间，当前出差编号，业务经理为当前用户的所有客户名称，以及注入微信jssdk需要的字段添加到模型，并转发到visitAdd.jsp
     */
    @RequestMapping("/visitAddInit")
    public ModelAndView visitAddInit (HttpSession session,HttpServletResponse response) throws IOException {
        ModelAndView mv = new ModelAndView("forward:/jsp/visit/visitAdd.jsp");
        String uname = (String) session.getAttribute("user");
        List<String> allCompanysNameList = visitService.getAllCompanys(uname);
        String businessStartTime = visitService.getbusinessStartTime(uname);
        String businessNo = visitService.getbusinessNo(uname);
        Integer onBusiness = businessService.getOnBusiness(uname);
        //如果不在出差状态，则弹出提示并跳转到主页面
        if(0==onBusiness){
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write("<script laguage='javascript'>alert('您还没有出差计划，请前往出差模块登记出差计划');window.location.href='/natergy-h5/jsp/main.jsp';</script>");
            return null;
        }
        WxToken wxToken = wxUtils.getWxToken("/natergy-h5/visit/visitAddInit");
        //添加字段到模型
        mv.addObject("businessStartTime", JSON.toJSONString(businessStartTime));
        mv.addObject("businessNo", JSON.toJSONString(businessNo));
        mv.addObject("allCompanysNameList", JSON.toJSONString(allCompanysNameList));
        mv.addObject("signature", wxToken.getSignature());
        mv.addObject("timestamp", wxToken.getTimestamp());
        mv.addObject("noncestr", wxToken.getNoncestr());
        mv.addObject("appId", wxToken.getAppId());
        return mv;
    }

    /**
     * 保存地产跟进
     * @param visit 前台提交的拜访记录对象
     * @return 是否保存成功
     */
    //@NoRepeatSubmit
    @RequestMapping("/save")
    public Integer saveVisit(@RequestBody Visit visit, HttpSession session) throws Exception {
        String uname = (String)session.getAttribute("user");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        visit.setUser(uname);
        visit.setDate(sdf.format(new Date()));
        return visitService.saveVisit(visit);
    }

    /**
     * 更新地产跟进
     * @param visit 前台提交的拜访记录对象
     * @return 是否更新成功
     */
    @RequestMapping("/update")
    @ResponseBody
    public Integer updateVisit(@RequestBody Visit visit,HttpSession session) {
        String uname = (String) session.getAttribute("user");
        return visitService.updateVisit(visit,uname);
    }


    /**
     * 下拉重新加载拜访记录
     * @return 返回重新查询的最后10条拜访记录
     */
    @RequestMapping("/refresh")
    public List<Visit> visitRefresh(HttpSession session) {
        String uname = (String) session.getAttribute("user");
        return visitService.refreshVisit(uname);
    }

    /**
     * 上拉加载更多拜访记录
     * @param limit 当前页面显示的拜访记录条数
     * @return 返回从limit开始后面的5条拜访记录
     */
    @RequestMapping("/reload")
    public List<Visit> visitReload(Integer limit,HttpSession session) {
        String uname = (String) session.getAttribute("user");
        return visitService.reloadVisit(uname,limit);
    }

    /**
     * 查询某个出差编号关联的所有拜访记录
     * @param businessNo 出差编号
     * @return 返回该出差编号关联的所有拜访记录
     */
    @RequestMapping("/selectByBusinessNo")
    public List<Visit> selectVisitByBusinessNo(@RequestBody String businessNo) {
        return visitService.selectVisitByBusiness(businessNo);
    }

    /**
     * 查询某个出差编号关联的所有拜访记录的客户名称
     * @param businessNo 出差编号
     * @return 返回该出差编号关联的拜访记录的客户名称Set
     */
    @RequestMapping("/getVisitCustomerNameByBusinessNo")
    public Set<String> getVisitCustomerNameByBusinessNo(@RequestBody String businessNo){
        List<String>resultList = visitService.selectVisitCustomerNameByBusiness(businessNo);
        return new HashSet<>(resultList);
    }

    /**
     * 获取某个出差编号关联的某个客户的全部拜访记录
     * @param customerName 客户名称
     * @param businessNo 出差编号
     * @return 返回某个出差编号关联的某个客户的拜访记录列表
     */
    @RequestMapping("/getVisitByAjax")
    public List<Visit> getVisitByAjax(@RequestParam String customerName, @RequestParam String businessNo)  {
        return visitService.getVisitsByAjax(customerName,businessNo);
    }

    /**
     * 获取某个客户的全部拜访记录
     * @param customerName 客户名称
     * @return 返回某个客户全部的拜访记录列表
     */
    @RequestMapping("/getVisitInfoByAjax")
    public List<Visit> getVisitInfoByAjax(@RequestParam String customerName, HttpSession session)  {
        String uname = (String) session.getAttribute("user");
        return visitService.getVisitsInfoByAjax(customerName,uname);
    }

    /**
     * 获取客户的地址信息
     * @param customerName 客户名
     * @return 返回该客户的地址信息
     */
    @RequestMapping("/getAddress")
    public ResultOfAddress getAddress(@RequestParam String customerName,HttpSession session)  {
        String uname = (String) session.getAttribute("user");
        return followUpService.getAddressInfo(customerName,uname);
    }

    /**
     * 删除拜访记录
     * @param id 拜访记录id
     * @return 返回是否删除成功
     */
    @RequestMapping("/delete")
    public Integer deleteVisit(@RequestParam String id){
        return visitService.deleteVisit(id);
    }


}
