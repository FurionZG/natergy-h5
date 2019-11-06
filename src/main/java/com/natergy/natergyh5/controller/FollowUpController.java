package com.natergy.natergyh5.controller;


import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.FollowUp;
import com.natergy.natergyh5.entity.ResultOfAddress;
import com.natergy.natergyh5.entity.WxToken;
import com.natergy.natergyh5.service.FollowUpService;
import com.natergy.natergyh5.utils.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 地产跟进控制器
 * @author 杨枕戈
 */
@RestController
@RequestMapping("/followUp")
public class FollowUpController {
    @Value("${natergy.appId}")
    private String appId;
    @Value("${natergy.appSecret}")
    private String appSecret;
    @Value("${natergy.host}")
    private String host;

    @Autowired
    private WxUtils wxUtils;
    @Autowired
    private FollowUpService followUpService;

    /**
     * 初始化地产跟进
     * @return 将当前登录客户的最后10条地产跟进记录添加到模型，并转发到followUp.jsp
     */
    @RequestMapping("/init")
    public ModelAndView followUpInit(HttpSession session){
        ModelAndView mv = new ModelAndView("forward:/jsp/followUp/followUp.jsp");
        String uname =  (String)session.getAttribute("user");
        List<FollowUp> resultList = followUpService.getFollowUpByUser(uname);
        mv.addObject("followUpList", JSON.toJSONString(resultList));
        return mv;
    }

    /**
     * 初始化添加地产跟进
     * @return 将业务经理为登录用户的所有客户名称，调用jssdk的url，时间戳，加密字符串保存到模型，并转发到followUpAdd.jsp
     */
    @RequestMapping("/followUpAddInit")
    public ModelAndView followUpAddInit (HttpSession session){
        ModelAndView mv = new ModelAndView("forward:/jsp/followUp/followUpAdd.jsp");
        String uname = (String) session.getAttribute("user");
        List<String> allCompanysNameList = followUpService.getAllCompanys(uname);
        WxToken wxToken = wxUtils.getWxToken("/natergy-h5/followUp/followUpAddInit");
        //添加到模型中
        mv.addObject("allCompanysNameList", JSON.toJSONString(allCompanysNameList));
        mv.addObject("signature", wxToken.getSignature());
        mv.addObject("timestamp", wxToken.getTimestamp());
        mv.addObject("noncestr", wxToken.getNoncestr());
        mv.addObject("appId", wxToken.getAppId());
        return mv;
    }

    /**
     * 获取某个客户的地址信息
     * @param companyName 客户名称
     * @return 返回该客户的地址信息
     */
    @RequestMapping("/getAddress")
    public ResultOfAddress getAddress(@RequestParam("name")String companyName, HttpSession session) {
        String uname = (String) session.getAttribute("user");
        return followUpService.getAddressInfo(companyName,uname);
    }

    /**
     * 保存地产跟进
     * @param followUp 前台提交的地产跟进对象
     * @return 返回是否保存成功
     */
    @RequestMapping("/save")
    public Integer saveFollowUp(@RequestBody FollowUp followUp,HttpSession session) throws Exception {
        String uname = (String) session.getAttribute("user");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒");
        followUp.setUser(uname);
        followUp.setDate(sdf.format(new Date()));
        return followUpService.saveFollowUp(followUp);
    }

    /**
     * 下拉刷新地产跟进
     * @return 返回重新查询的最后10条地产跟进记录
     */
    @RequestMapping("/refresh")
    public List<FollowUp> followUpRefresh(HttpSession session)  {
        String uname = (String) session.getAttribute("user");
        return followUpService.refreshFollowUp(uname);
    }

    /**
     * 上拉加载更多地产跟进记录
     * @param limit 页面上显示的地产跟进记录条数
     * @return 返回从limit开始后面的5条地产跟进记录
     */
    @RequestMapping("/reload")
    public List<FollowUp> followUpReload(Integer limit,HttpSession session)  {
        String uname = (String) session.getAttribute("user");
        return followUpService.reloadFolloUp(uname,limit);
    }

    /**
     * 更新地产跟进
     * @param followUp 前台提交的地产跟进对象
     * @return 返回是否更新成功
     */
    @RequestMapping("/update")
    @ResponseBody
    public Integer updateFollowUp(@RequestBody FollowUp followUp,HttpSession session) {
        String uname = (String) session.getAttribute("user");
        return followUpService.updateFollowUp(followUp,uname);
    }
}
