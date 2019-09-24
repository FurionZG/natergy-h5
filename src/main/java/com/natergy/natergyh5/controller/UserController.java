package com.natergy.natergyh5.controller;


import com.natergy.natergyh5.entity.User;
import com.natergy.natergyh5.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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
}
