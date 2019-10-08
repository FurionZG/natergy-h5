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




    public List<String> getAllCompanys(String uname) {
        return customerDao.getCustomersByUser(uname);
    }

    @Transactional
    public Integer saveBusiness(Visit visit) throws Exception {
        WXJsSdk d = new WXJsSdk();
        Map map1 = d.getAccessToken(appId, appSecret);
        String accessToken = (String) map1.get("accessToken");
        List<Option> options = new ArrayList<>();
        List<String> list = visit.getImages();
        if(""!=list.get(0)) {
            for (String media_id : list) {
                String requestUrl = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
                requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID", media_id);
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
                //System.out.println(ftpClient.storeFile(path,bis));
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
        Integer reten1 = visitDao.saveBusiness(visit);
        Integer reten2 = visitDao.updateCustomer(visit);
        return reten1*reten2;
    }

    public String getbusinessStartTime(String uname) {
        return businessDao.getbusinessStartTime(uname);
    }

    public String getbusinessNo(String uname) {
        return businessDao.getbusinessNo(uname);
    }

    public List<Visit> getVisitsByUser(String uname) {
        List<Visit> resultList=visitDao.getVisitsByUser(uname);
        for (Visit visit : resultList) {
            String imgStr = visit.getImgStr();
            String[] images = imgStr.split("/");
            List<String> imgesList = new ArrayList<>();
            //不要第一个值，因为第一个值是""
            for (int i = 1; i < images.length; i++) {
                String imageUrl = optionsDao.queryOption(images[i]);
                imgesList.add(imageUrl);
            }
            visit.setImages(imgesList);
        }
        return  resultList;

    }

    @Transactional
    public Integer updateVisit(Visit visit, String uname) {
        String customerId = visitDao.selectCustomerIdByVisitId(visit.getId());
        visit.setCustomerId(customerId);
        Integer reten1= visitDao.updateVisit(visit);
        Integer reten2=customerDao.updateVisit(visit,uname);
        return  reten1*reten2;
    }
}
