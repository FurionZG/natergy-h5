package com.natergy.natergyh5.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.natergy.natergyh5.entity.User;
import com.natergy.natergyh5.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录的方法
     * @param user   用户名
     * @param request   密码
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestBody User user , HttpServletRequest request) {
        Integer reten =  userService.checkUser(user);
        if(1==reten){
            request.getSession().setAttribute("user",user.getUname());
        }
        return String.valueOf(reten);

    }

    @RequestMapping("/wxOpenId")
    public void wxOpenId(@RequestBody JSONObject code,HttpServletResponse response,HttpServletRequest request) throws IOException {
        String result = userService.checkWxOpenId((String)code.get("code"));
        if(!result.equals("0")){
            request.getSession().setAttribute("user",result);
        }
        response.getWriter().write(JSON.toJSONString(result));
    }

    @RequestMapping("/logout")
    @ResponseBody
    public ModelAndView logout(ModelAndView mv, HttpServletRequest request) {
        request.getSession().removeAttribute("user");
        mv.setViewName("forward:/login.jsp");
        return mv;

    }


}
