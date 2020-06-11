package com.natergy.natergyh5.service;

import com.natergy.natergyh5.dao.BusinessApplyMapper;
import com.natergy.natergyh5.dao.UserMapper;
import com.natergy.natergyh5.entity.BusinessApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 
 * @Author 杨枕戈
 * @Date 2020-03-28 9:20
 * @Version 1.0
 * 
 */
@Service
public class BusinessApplyService {


    @Autowired
    private BusinessApplyMapper businessApplyMapper;
    @Autowired
    private UserMapper userDao;

    public List<BusinessApply> getBusinessApplyList(String uname) {
        return businessApplyMapper.getBusinessApplyList(uname);

    }

    public Integer save(BusinessApply businessApply, String uname) throws ParseException {
        SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        businessApply.setDate(new SimpleDateFormat("yyyy年MM月dd日HH时mm分").format(new Date()));
        businessApply.setStatus("新");
        businessApply.setUser(uname);

        String nowYear = new SimpleDateFormat("yyyy").format(new Date());
        String yearCount = businessApplyMapper.getYearCount(uname, nowYear);
        String userNo = userDao.getUserNo(uname);
        String businessApplyNo = "SQ-"+userNo + "-" + nowYear + "-" + (Integer.parseInt(yearCount)+1);

        Date startDate = simpleDateFormat.parse(businessApply.getpBusinessStartTime());
        Date endDate = simpleDateFormat.parse(businessApply.getpBusinessEndTime());
        businessApply.setpDays(String.valueOf((endDate.getTime()-startDate.getTime())/60/60/24/1000));
        businessApply.setNo(businessApplyNo);
        return businessApplyMapper.save(businessApply);
    }

    public List<BusinessApply> reloadBusinessApply(Integer limit, String uname) {
        return businessApplyMapper.reloadBusinessApply(limit,uname);
    }

    public List<BusinessApply> refreshBusinessApply(String uname) {
        return businessApplyMapper.refreshBusinessApply(uname);
    }

    public Integer update(BusinessApply businessApply) throws ParseException {
        SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日");
        Date startDate = simpleDateFormat.parse(businessApply.getpBusinessStartTime());
        Date endDate = simpleDateFormat.parse(businessApply.getpBusinessEndTime());
        businessApply.setpDays(String.valueOf((endDate.getTime()-startDate.getTime())/60/60/24/1000));
        return businessApplyMapper.update(businessApply);
    }
}
