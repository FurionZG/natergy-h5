package com.natergy.natergyh5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 主页控制器
 * @author 杨枕戈
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(){
        return "forward:login.jsp";
    }
}
