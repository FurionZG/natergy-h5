package com.natergy.natergyh5.controller.infactory;

import com.natergy.natergyh5.service.infactory.DacangService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 
 * @Author 杨枕戈
 * @Date 2020-04-01 16:09
 * @Version 1.0
 * 
 */
@RestController
@RequestMapping("/infactory/dacang")
public class DacangController {
    @Autowired
    private DacangService dacangService;

    @RequestMapping("/{no}")
    public String transform (@PathVariable String no){
        return dacangService.transform(no);
    }
}
