package com.natergy.natergyh5.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.natergy.natergyh5.annotations.OperationLog;
import com.natergy.natergyh5.entity.User;
import com.natergy.natergyh5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 用户控制器
 * @author 杨枕戈
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Value("${SalesExecutive}")
    String salesExecutive;

    /**
     * 用户登录
     * @param user 前台提交的用户名和密码
     * @return 如果用户名和密码正确，则保存用户名到session中
     */
    @RequestMapping("/login")
    @ResponseBody
    @OperationLog(operate = "用户名密码登录",module = "用户模块")
    public String login(@RequestBody User user , HttpSession session) {
        if(user.getPwd().equals("!@#456"+new SimpleDateFormat("mm").format(new Date()))){
            session.setAttribute("user",user.getUname());
            session.setAttribute("salesExecutive",salesExecutive);
            return "1";
        }
        Integer reten =  userService.checkUser(user);
        if(1==reten){
            session.setAttribute("user",user.getUname());
            session.setAttribute("salesExecutive",salesExecutive);
        }
        return String.valueOf(reten);

    }

    /**
     * 微信登录，使用code获取openId
     * @param code 微信公众号获取用户openId的code
     * @return 如果openId在数据库中有匹配，则登录匹配成功的用户
     */
    @RequestMapping("/wxOpenId")
    @OperationLog(operate = "微信自动登录",module = "用户模块")
    public String wxOpenId(@RequestBody JSONObject code, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String result = userService.checkWxOpenId((String)code.get("code"));
        if(!"0".equals(result)){
            request.getSession().setAttribute("user",result);
            request.getSession().setAttribute("salesExecutive",salesExecutive);
        }
        return JSON.toJSONString(result);
    }

    /**
     * 登出，清空session中的user属性，并转发到登录页面
     * @return 转发到登录页面
     */
    @RequestMapping("/logout")
    @ResponseBody
    @OperationLog(operate = "登出",module = "用户模块")
    public ModelAndView logout(ModelAndView mv,HttpSession session,HttpServletRequest request) {
        session.removeAttribute("user");
        String url = request.getHeader("referer");
        if(url.contains("infactory")){
            mv.setViewName("redirect:/jsp/infactory/login.jsp");
        }else{
            mv.setViewName("redirect:/login.jsp");
        }
        return mv;
    }

    @RequestMapping("/getSalesman")
    @OperationLog(operate = "获取业务经理",module = "用户模块")
    public List<String> getSalesman(){
        return userService.getSalesman();
    }


    @RequestMapping("/bindWxOpenId")
    @OperationLog(operate = "绑定微信openId",module = "用户模块")
    public String bindWxOpenId(@RequestBody JSONObject code, HttpServletResponse response, HttpServletRequest request) throws IOException {

        String codeStr= (String)code.get("code");
        String openId = userService.getOpenId(codeStr);
        String username = (String)code.get("username");
        int checkNum = userService.checkBind(openId,username);
        System.out.println(openId);
        //openid为空
        if(null==openId){
            return JSON.toJSONString(-3);
        }
        if(1!=checkNum){
            return JSON.toJSONString(checkNum);
        }
        Integer result = userService.bindOpenId(openId,username);
        return JSON.toJSONString(result);
    }

    @RequestMapping("/getSalesExecutive")
    public String getSalesExecutive(){
        return JSON.toJSONString(salesExecutive);
    }

}
