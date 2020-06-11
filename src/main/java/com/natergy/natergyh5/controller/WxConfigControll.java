package com.natergy.natergyh5.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @Author 杨枕戈
 * @Date 2020-02-28 10:20
 * @Version 1.0
 * 
 */

@Controller
@RequestMapping({"/"})
public class WxConfigControll {
    @RequestMapping({"MP_verify_0SpIfYpfLRMIbxYd.txt"})
    @ResponseBody
    private String returnConfigFile() {
        //把MP_verify_xxxxxx.txt中的内容返回
        return "0SpIfYpfLRMIbxYd";
    }
}
