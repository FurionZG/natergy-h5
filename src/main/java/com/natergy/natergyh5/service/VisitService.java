package com.natergy.natergyh5.service;

import com.natergy.natergyh5.dao.BusinessMapper;
import com.natergy.natergyh5.dao.CustomerMapper;
import com.natergy.natergyh5.dao.OptionsMapper;
import com.natergy.natergyh5.dao.VisitMapper;
import com.natergy.natergyh5.entity.Visit;
import com.natergy.natergyh5.entity.Option;
import com.natergy.natergyh5.entity.WXJsSdk;
import com.natergy.natergyh5.utils.FtpUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 销售拜访模块业务层
 * @author 杨枕戈
 */
@Service
public class VisitService {
    @Autowired
    private VisitMapper visitDao;
    @Autowired
    private BusinessMapper businessDao;
    @Autowired
    private CustomerMapper customerDao;
    @Autowired
    private OptionsMapper optionsDao;
    @Value("${natergy.appId}")
    private String appId;
    @Value("${natergy.appSecret}")
    private String appSecret;
    @Value("${natergy.host}")
    private String host;

    /**
     * 查询登录用户有拜访记录的客户名
     * @param uname 登录用户的用户名
     * @return 返回登录用户有拜访记录的客户名列表
     */
    public List<String> getAllCompanys(String uname) {
        return customerDao.getCustomersByUser(uname);
    }

    /**
     * 保存拜访记录
     * @param visit 拜访对象
     * @return 返回是否保存成功
     */
    @Transactional
    public Integer saveVisit(Visit visit) throws Exception {
        WXJsSdk d = new WXJsSdk();
        Map map1 = d.getAccessToken(appId, appSecret);
        String accessToken = (String) map1.get("accessToken");
        List<Option> options = new ArrayList<>();
        List<String> list = visit.getImages();
        if (!"".equals(list.get(0))) {
            for (String mediaId : list) {
                String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
                requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", mediaId);
                URL url = new URL(requestUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setDoInput(true);
                conn.setRequestMethod("GET");
                //图片缓冲输入流
                BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
                //获取FtpClient
                FTPClient ftpClient = FtpUtils.getFtpClient();
                ftpClient.changeWorkingDirectory("natergy");
                ftpClient.enterLocalPassiveMode();
                String fileName = visit.getUser() + "-" + new SimpleDateFormat("yyyy年MM月dd日HH时mm分ss秒").format(new Date()) + "-" + (UUID.randomUUID().toString().replace("-", "")) + ".jpg";
                String path = new String(("./pic/" + fileName).getBytes("gbk"), "iso-8859-1");
                ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
                if (ftpClient.storeFile(path, bis)) {
                    Option option = new Option();
                    option.setName(fileName);
                    option.setPos("销售拜访");
                    options.add(option);
                } else {
                    throw new Exception("照片保存错误");
                }
            }
            optionsDao.saveOptions(options);
            StringBuffer sb = new StringBuffer();
            for (Option option : options) {
                sb.append("/");
                sb.append(option.getId());
            }
            visit.setImgStr(sb.toString());
        }

        visit.setCustomerNo(visitDao.selectCustomerNo(visit.getCustomerName(), visit.getUser()));
        visit.setCustomerId(visitDao.selectCustomerId(visit.getCustomerName(), visit.getUser()));
        if(null==visit.getCustomerId()){
            visit.setCustomerId("0");
        }
        Integer reten1 = visitDao.saveBusiness(visit);
        Integer reten2 = visitDao.updateCustomer(visit);
        return reten1*reten2;
    }

    /**
     * 查询登录用户当前出差记录的开始时间
     * @param uname 登录用户的用户名
     * @return 返回当前出差记录的开始时间
     */
    public String getbusinessStartTime(String uname) {
        return businessDao.getbusinessStartTime(uname);
    }

    /**
     * 查询登录用户当前出差的出差编号
     * @param uname 登录用户的用户名
     * @return 返回登录用户当前出差的出差编号
     */
    public String getbusinessNo(String uname) {
        return businessDao.getbusinessNo(uname);
    }

    /**
     * 查询登录用户的最后10条拜访记录，并且将图片附件文件名注入到对应的拜访记录中
     * @param uname 登录用户的用户名
     * @return 返回登录用户的最后10条拜访记录
     */
    public List<Visit> getVisitsByUser(String uname) {
        List<Visit> resultList = visitDao.getVisitsByUser(uname);
        detailImages(resultList);
        return resultList;

    }

    /**
     * 更新拜访记录
     * @param visit 拜访记录对象
     * @param uname 登录用户的用户名
     * @return 返回是否更新成功
     */
    @Transactional
    public Integer updateVisit(Visit visit, String uname) {
        String customerId = visitDao.selectCustomerIdByVisitId(visit.getId());
        visit.setCustomerId(customerId);
        Integer reten1 = visitDao.updateVisit(visit);
        Integer reten2 = customerDao.updateVisit(visit, uname);
        return reten1 * reten2;
    }

    /**
     * 重新查询登录用户的销售拜访记录
     * @param uname 登录用户的用户名
     * @return 返回登录用户最后10条拜访记录
     */
    public List<Visit> refreshVisit(String uname) {
        List<Visit> resultList = visitDao.getVisitsByUser(uname);
        detailImages(resultList);
        return resultList;
    }

    /**
     * 加载更多登录用户的拜访记录
     * @param uname 登录用户的用户名
     * @param limit 页面显示的拜访记录条数
     * @return 返回从limit开始之后的5条拜访记录
     */
    public List<Visit> reloadVisit(String uname, Integer limit) {
        List<Visit> resultList = visitDao.reloadVisit(limit, uname);
        detailImages(resultList);
        return resultList;
    }

    /**
     * 处理图片附件的方法，将图片附件的文件名注入到拜访对象中
     * @param resultList 拜访对象列表
     */
    private void detailImages(List<Visit> resultList){
        for (Visit visit : resultList) {
            List<String> imgesList = new ArrayList<>();
            String imgStr = visit.getImgStr();
            if (null != imgStr) {
                String[] images = imgStr.split("/");
                //不要第一个值，因为第一个值是""
                for (int i = 1; i < images.length; i++) {
                    String imageUrl = optionsDao.queryOption(images[i]);
                    imgesList.add(imageUrl);
                }
            }
            visit.setImages(imgesList);
        }
    }

    /**
     * 查询某个出差编号关联的拜访记录
     * @param businessNo 出差编号
     * @return 返回某个出差编号关联的拜访记录列表
     */
    public List<Visit> selectVisitByBusiness(String businessNo) {
        List<Visit> resultList=visitDao.selectVisitByBusiness(businessNo);
        detailImages(resultList);
        return resultList;
    }

    /**
     * 查询某个出差编号关联的拜访记录中所有的客户名称
     * @param businessNo 出差编号
     * @return 返回某个出差编号关联的拜访记录中的所有客户名称
     */
    public List<String> selectVisitCustomerNameByBusiness(String businessNo) {
        return visitDao.selectVisitCustomerNameByBusiness(businessNo);
    }

    /**
     * 通过客户名称和出差编号查询拜访记录
     * @param customerName 客户名称
     * @param businessNo 出差编号
     * @return 返回某一出差编号下对某一个客户的所有拜访记录
     */
    public List<Visit> getVisitsByAjax(String customerName, String businessNo) {
        List<Visit> resultList=visitDao.getVisitsByAjax(customerName,businessNo);
        detailImages(resultList);
        return resultList;
    }

    /**
     * 查询登录用户所有拜访记录中客户名称Set
     * @param uname 登录用户的用户名
     * @return 返回登录用户所有拜访记录中客户名称的Set集
     */
    public Set<String> getVisitsCustomerNames(String uname) {
        return new HashSet(visitDao.getVisitsCustomerNames(uname));
    }

    /**
     * 查询登录用户对某个客户的拜访记录
     * @param customerName 需要查询的客户名
     * @param uname 登录用户的用户名
     * @return 返回登录用户对某个客户的最后10条拜访记录
     */
    public List<Visit> getVisitsInfoByAjax(String customerName,String uname) {
        List<Visit> resultList=visitDao.getVisitsInfoByAjax(customerName,uname);
        detailImages(resultList);
        return resultList;
    }

    /**
     * 删除拜访记录
     * @param id 需要删除的拜访记录的id
     * @return 是否删除成功
     */
    public Integer deleteVisit(String id) {
        return visitDao.deleteVisit(id);
    }
}
