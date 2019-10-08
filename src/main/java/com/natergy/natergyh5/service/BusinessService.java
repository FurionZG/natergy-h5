package com.natergy.natergyh5.service;

import com.natergy.natergyh5.dao.BusinessMapper;
import com.natergy.natergyh5.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class BusinessService {

    @Autowired
    private BusinessMapper businessDao;
    @Autowired
    private UserMapper userDao;

    public Integer getOnBusiness(String uname){
        return businessDao.getOnBusiness(uname);
    }

    public Integer setBusinessBegin(String uname) {
        String nowTime  =new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date());
        String nowYear  =new SimpleDateFormat("yyyy").format(new Date());
        String yearCount = businessDao.getYearCount(uname,nowYear);
        String userNo = userDao.getUserNo(uname);
        String businessNo = userNo+"-"+nowYear+"-"+yearCount;
        return businessDao.setBusinessBegin(uname,nowTime,businessNo);
    }

    public Integer setBusinessEnd(String uname) {
        String nowTime  =new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date());
        return businessDao.setBusinessEnd(uname,nowTime);
    }
}
