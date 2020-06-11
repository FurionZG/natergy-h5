package com.natergy.natergyh5.service;

import com.natergy.natergyh5.dao.ApplyMapper;
import com.natergy.natergyh5.entity.Apply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: 杨枕戈
 * @Date: 2019/11/27 13:20
 */
@Service
public class ApplyService {

    @Autowired
    private ApplyMapper applyMapper;

    @Value("${SalesExecutive}")
    String salesExecutive ;

    public List<Apply> getApplysByUser(String uname) {
        return applyMapper.getApplysByUser(uname);
    }

    public List<Apply> refreshApply(String uname) {
        return applyMapper.getApplysByUser(uname);
    }

    public List<Apply> reloadApply(String uname, Integer limit) {
        return applyMapper.reloadApply(uname,limit);
    }

    public Integer saveApply(Apply apply, String uname) {
        apply.setUser(uname);
        apply.setStatus("新");
        apply.setDate(new SimpleDateFormat("yyyy年MM月dd日").format(new Date()));
        Integer count = applyMapper.applyCount( "SQ"+apply.getDate().substring(2,7).replace("年",""));
        apply.setApplyNo("SQ"+apply.getDate().substring(2,7).replace("年","")+String.format("%04d", count+1));
        return applyMapper.applySave(apply);
    }

    @Transactional
    public Integer updateApply(Apply apply, String uname) {
        String applyNo = applyMapper.selectApplyNo(apply.getId());
        String date = applyMapper.selectDate(apply.getId());
        applyMapper.applyDelete(apply.getId());
        apply.setUser("/"+uname+salesExecutive);
        apply.setStatus("新");
        apply.setDate(date);
        apply.setApplyNo(applyNo);
        return applyMapper.applySave(apply);
    }

    public List<String> getCustomerNames(String uname) {
        return new ArrayList(new HashSet(applyMapper.getCustomerNames(uname)));
    }

    public List<Apply> getApplyByAjax(String customerName, String uname) {
        return applyMapper.getApplyByAjax(customerName,uname);
    }

    public Integer saveApproval(Apply apply) {
        return applyMapper.saveApproval(apply);
    }
}
