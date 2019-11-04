package com.natergy.natergyh5.service;

import com.natergy.natergyh5.dao.BusinessMapper;
import com.natergy.natergyh5.dao.OptionsMapper;
import com.natergy.natergyh5.dao.UserMapper;
import com.natergy.natergyh5.entity.Business;
import com.natergy.natergyh5.utils.SaveImageToServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class BusinessService {

    @Autowired
    private BusinessMapper businessDao;
    @Autowired
    private UserMapper userDao;
    @Autowired
    private OptionsMapper optionDao;
    @Value("${natergy.appId}")
    private String appId;
    @Value("${natergy.appSecret}")
    private String appSecret;
    @Value("${natergy.host}")
    private String host;
    @Autowired
    private SaveImageToServer saveImageToServer;


    public Integer getOnBusiness(String uname) {
        return businessDao.getOnBusiness(uname);
    }

    @Transactional
    public Integer setBusinessBegin(String uname) throws Exception {
        String nowTime = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date());
        String nowYear = new SimpleDateFormat("yyyy").format(new Date());
        String yearCount = businessDao.getYearCount(uname, nowYear);
        String userNo = userDao.getUserNo(uname);
        String businessNo = userNo + "-" + nowYear + "-" + (Integer.parseInt(yearCount)+1);

        return businessDao.setBusinessBegin(uname, nowTime, businessNo);
    }

    @Transactional
    public Integer setBusinessEnd(String uname) throws Exception {
        String nowTime = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date());
        return businessDao.setBusinessEnd(uname, nowTime);
    }

    public List<Business> getBusinessByUser(String uname) {
        List<Business> resultList = businessDao.getBusinessByUser(uname);
        return loadImageName(resultList);
    }

    @Transactional
    public Integer updateBusiness(Business business, String uname) throws Exception {
        String startSrc = saveImageToServer.saveImageToServer(business.getStartImage(), uname, "出差计划");
        String endSrc = saveImageToServer.saveImageToServer(business.getEndImage(), uname, "出差计划");
        business.setStartImage(startSrc);
        business.setEndImage(endSrc);
        return businessDao.updateBusiness(business);
    }

    public List<Business> getBusinessByLimit(String uname, Integer limit) {
        List<Business> resultList = businessDao.getBusinessByLimit(uname,limit);
        return loadImageName(resultList);
    }

    private List<Business> loadImageName(List<Business> resultList) {
        for (Business business : resultList) {
            if(!business.getEndImage().equals("")){
                business.setEndImage(optionDao.queryOption(business.getEndImage().substring(1)));
            }
            if(!business.getStartImage().equals("")){
                business.setStartImage(optionDao.queryOption(business.getStartImage().substring(1)));
            }
        }
        return resultList;
    }
}
