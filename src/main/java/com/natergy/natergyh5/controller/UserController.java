package com.natergy.natergyh5.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.natergy.natergyh5.entity.User;
import com.natergy.natergyh5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户控制器
 * @author 杨枕戈
 */
@RestController
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     * @param user 前台提交的用户名和密码
     * @return 如果用户名和密码正确，则保存用户名到session中
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody User user , HttpSession session) {
        Integer reten =  userService.checkUser(user);
        if(1==reten){
            session.setAttribute("user",user.getUname());
        }
        return String.valueOf(reten);

    }

    /**
     * 微信登录，使用code获取openId
     * @param code 微信公众号获取用户openId的code
     * @return 如果openId在数据库中有匹配，则登录匹配成功的用户
     */
    @RequestMapping("/wxOpenId")
    public String wxOpenId(@RequestBody JSONObject code, HttpServletResponse response, HttpServletRequest request) throws IOException {
        String result = userService.checkWxOpenId((String)code.get("code"));
        if(!"0".equals(result)){
            request.getSession().setAttribute("user",result);
        }
        return JSON.toJSONString(result);
    }

    /**
     * 登出，清空session中的user属性，并转发到登录页面
     * @return 转发到登录页面
     */
    @RequestMapping("/logout")
    @ResponseBody
    public ModelAndView logout(ModelAndView mv,HttpSession session) {
        session.removeAttribute("user");
        mv.setViewName("forward:/login.jsp");
        return mv;

    }


}
