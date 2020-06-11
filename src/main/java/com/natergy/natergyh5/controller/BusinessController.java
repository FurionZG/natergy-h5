package com.natergy.natergyh5.controller;

import com.alibaba.fastjson.JSON;
import com.natergy.natergyh5.entity.Business;
import com.natergy.natergyh5.entity.wxEntity.WxToken;
import com.natergy.natergyh5.service.BusinessService;
import com.natergy.natergyh5.utils.WxUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import java.util.List;


/**
 * 出差控制器
 * @author 杨枕戈
 */
@RestController
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
    @Autowired
    private WxUtils wxUtils;
    /**
     * 初始化出差
     * @return 将出差状态和出差列表保存在模型中，并转发到business.jsp
     */
    @RequestMapping("/init")
    public ModelAndView businessInit(HttpSession session)  {
        ModelAndView mv = new ModelAndView("forward:/jsp/business/business.jsp");
        String uname = (String) session.getAttribute("user");
        Integer onBusinessCount=businessService.getOnBusiness(uname);
        List<Business> businessList=businessService.getBusinessByUser(uname);
        WxToken wxToken = wxUtils.getWxToken("/natergy-h5/jsp/business/businessEdit.jsp");
        //添加到模型中
        mv.addObject("signature", wxToken.getSignature());
        mv.addObject("timestamp", wxToken.getTimestamp());
        mv.addObject("noncestr", wxToken.getNoncestr());
        mv.addObject("appId", wxToken.getAppId());
        mv.addObject("onBusinessCount", JSON.toJSONString(onBusinessCount));
        mv.addObject("businessList",JSON.toJSONString(businessList));
        return mv;
    }

    /**
     * 开始出差
     * @return 返回开始出差是否成功
     */
    @RequestMapping("/begin")
    public Integer businessBegin(HttpSession session)  {
        String uname = (String) session.getAttribute("user");
        return businessService.setBusinessBegin(uname);
    }

    /**
     * 结束出差
     * @return 返回结束出差是否成功
     */
    @RequestMapping("/end")
    public Integer businessEnd(HttpSession session)  {
        String uname = (String) session.getAttribute("user");
        return businessService.setBusinessEnd(uname);
    }

    /**
     * 更新出差信息
     * @param business 被选中的出差信息
     * @return 是否更新成功
     */
    @RequestMapping("/update")
    public Integer businessUpdate(@RequestBody Business business, HttpSession session) throws Exception {
        String uname = (String) session.getAttribute("user");
        return businessService.updateBusiness(business,uname);
    }

    /**
     * 下拉刷新出差信息
     * @return 重新查询的出差信息
     */
    @RequestMapping("/refresh")
    public List<Business> businessRefresh(HttpSession session) {
        String uname = (String) session.getAttribute("user");
        return businessService.getBusinessByUser(uname);
    }

    /**
     * 上拉加载更多的出差信息
     * @param limit 当前展示的出差信息条数
     * @return 返回从limit开始后面的5条出差信息
     */
    @RequestMapping("/reload")
    public  List<Business> businessReload(Integer limit, HttpSession session)  {
        String uname = (String) session.getAttribute("user");
        return businessService.getBusinessByLimit(uname,limit);
    }
}
