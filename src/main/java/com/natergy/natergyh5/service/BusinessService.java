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

/**
 * 出差模块业务层
 * @author 杨枕戈
 */
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


    /**
     * 获取是否在出差状态
     * @param uname 登录用户的用户名
     * @return 是否在出差状态
     */
    public Integer getOnBusiness(String uname) {
        return businessDao.getOnBusiness(uname);
    }

    /**
     * 设置出差计划开始
     * @param uname 登录用户的用户名
     * @return 是否设置成功
     */
    @Transactional
    public Integer setBusinessBegin(String uname)  {
        String nowTime = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date());
        String nowYear = new SimpleDateFormat("yyyy").format(new Date());
        String yearCount = businessDao.getYearCount(uname, nowYear);
        String userNo = userDao.getUserNo(uname);
        String businessNo = userNo + "-" + nowYear + "-" + (Integer.parseInt(yearCount)+1);
        return businessDao.setBusinessBegin(uname, nowTime, businessNo);
    }

    /**
     * 设置出差结束
     * @param uname 登录用户的用户名
     * @return 是否设置成功
     */
    @Transactional
    public Integer setBusinessEnd(String uname)  {
        String nowTime = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒").format(new Date());
        return businessDao.setBusinessEnd(uname, nowTime);
    }

    /**
     * 获取当前用户的出差记录
     * @param uname 登录用户的用户名
     * @return 当前用户的最后10条出差记录
     */
    public List<Business> getBusinessByUser(String uname) {
        List<Business> resultList = businessDao.getBusinessByUser(uname);
        return loadImageName(resultList);
    }

    /**
     * 更新出差记录
     * @param business 出差对象
     * @param uname 登录用户的用户名
     * @return 是否更新成功
     */
    @Transactional
    public Integer updateBusiness(Business business, String uname) throws Exception {
        String startSrc = saveImageToServer.saveImageToServer(business.getStartImage(), uname, "出差计划");
        String endSrc = saveImageToServer.saveImageToServer(business.getEndImage(), uname, "出差计划");
        business.setStartImage(startSrc);
        business.setEndImage(endSrc);
        return businessDao.updateBusiness(business);
    }

    /**
     * 查询limit后的5条出差记录
     * @param uname 登录用户的用户名
     * @param limit 当前已显示的出差记录条数
     * @return 返回limit后的5条出差记录
     */
    public List<Business> getBusinessByLimit(String uname, Integer limit) {
        List<Business> resultList = businessDao.getBusinessByLimit(uname,limit);
        return loadImageName(resultList);
    }

    /**
     * 查询图片附件在Ftp服务器中的文件名
     * @param resultList 出差记录列表
     * @return 将每条出差记录的图片附件名添加到对应的Business对象中，返回出差记录列表
     */
    private List<Business> loadImageName(List<Business> resultList) {
        for (Business business : resultList) {
            if(!"".equals(business.getEndImage())){
                business.setEndImage(optionDao.queryOption(business.getEndImage().substring(1)));
            }
            if(!"".equals(business.getStartImage())){
                business.setStartImage(optionDao.queryOption(business.getStartImage().substring(1)));
            }
        }
        return resultList;
    }
}
