package com.natergy.natergyh5.controller;


import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.Working;
import com.natergy.natergyh5.entity.wxEntity.WxToken;
import com.natergy.natergyh5.service.WorkingService;
import com.natergy.natergyh5.utils.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * 工作进程控制器
 * @author 杨枕戈
 */
@RestController
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

    /**
     * 初始化工作进程
     * @return 将登录用户的工作进程列表，汇报人列表，可阅人信息添加到模型，并转发到working.jsp
     */
    @RequestMapping("/init")
    public ModelAndView workingInit(HttpSession session) {
        ModelAndView mv = new ModelAndView("forward:/jsp/working/working.jsp");
        String uname = (String)session.getAttribute("user");
        List<Working> resultList = workingService.getWorkings(uname);
        Set<String> workingsNameSet = workingService.getWorkingsName(uname);
        mv.addObject("workings", JSON.toJSONString(resultList));
        mv.addObject("workingNameSet", JSON.toJSONString(workingsNameSet));
        mv.addObject("salesExecutive", JSON.toJSONString(salesExecutive));
        return mv;
    }

    /**
     * 初始化添加工作进程
     * @return 将注入微信jssdk所需的字段添加到模型，并转发到workingAdd.jsp
     */
    @RequestMapping("/workingAddInit")
    public ModelAndView workingAddInit() {
        ModelAndView mv = new ModelAndView("forward:/jsp/working/workingAdd.jsp");
        WxToken wxToken = wxUtils.getWxToken("/natergy-h5/working/workingAddInit");
        mv.addObject("signature", wxToken.getSignature());
        mv.addObject("timestamp", wxToken.getTimestamp());
        mv.addObject("noncestr", wxToken.getNoncestr());
        mv.addObject("appId", wxToken.getAppId());
        return mv;
    }

    /**
     * 保存工作进程
     * @param working 前台提交的工作进程对象
     * @return 返回是否保存成功
     */
    @RequestMapping("/save")
    public Integer workingSave(@RequestBody Working working, HttpSession session) throws Exception {
        working.setUser((String) session.getAttribute("user"));
        return workingService.saveWorking(working);
    }


    /**
     * 更新工作进程
     * @param working 前台提交的工作今晨对象
     * @return 返回是否保存成功
     */
    @RequestMapping("/update")
    public Integer workingUpdate(@RequestBody Working working) throws Exception {
        return workingService.updateWorking(working);
    }

    /**
     * 初始化工作进程编辑
     * @return 将注入微信jssdk所需的字段添加到模型，并转发到workingEdit.jsp
     */
    @RequestMapping("/workingEditInit")
    public ModelAndView workingEditInit() {
        ModelAndView mv = new ModelAndView("forward:/jsp/working/workingEdit.jsp");
        WxToken wxToken = wxUtils.getWxToken("/natergy-h5/working/workingEditInit");
        mv.addObject("signature", wxToken.getSignature());
        mv.addObject("timestamp", wxToken.getTimestamp());
        mv.addObject("noncestr", wxToken.getNoncestr());
        mv.addObject("appId", wxToken.getAppId());
        return mv;
    }

    /**
     * 下拉刷新工作进程列表
     * @return 返回重新查询的工作进程列表
     */
    @RequestMapping("/refresh")
    public List<Working> workingRefresh(HttpSession session)  {
        String uname = (String) session.getAttribute("user");
        return workingService.getWorkings(uname);
    }

    /**
     * 上拉加载更多工作进程，如果搜索过某个汇报人的工作进程，则加载该汇报人更多的工作进程
     * @param name 搜索框中的汇报人
     * @param limit 当前页面显示的工作进程条数
     * @return 返回从limit开始后面5条工作进程记录
     */
    @RequestMapping("/reload")
    public List<Working> workingReload(String name, Integer limit, HttpSession session)  {
        String uname = (String) session.getAttribute("user");
        List<Working> resultList;
        if ("".equals(name)) {
            resultList = workingService.reloadWorkings(limit, uname);
        }else{
            resultList = workingService.reloadWorkingsByName(limit, uname,name);
        }
        return resultList;
    }

    /**
     * 获取某个汇报人的最近10条工作记录
     * @param name 汇报人姓名
     * @return 返回该汇报人的最近10条工作记录
     */
    @RequestMapping("/getWorkingsByName")
    public List<Working> getWorkingsByName(String name, HttpSession session) {
        String uname = (String) session.getAttribute("user");
        return workingService.getWorkingsByName(uname, name);

    }
}
