package com.natergy.natergyh5.controller;

import com.natergy.natergyh5.service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/refund")
public class RefundController {
    @Autowired
    private RefundService refundService ;

    @RequestMapping("/init")
    public void refundInit(){

    }
}
